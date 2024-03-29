package com.cya.service;

import com.cya.dao.BorrowMapper;
import com.cya.entity.Book;
import com.cya.entity.Borrow;
import com.cya.entity.Users;
import com.cya.repos.BorrowRepository;
import com.cya.util.consts.Constants;
import com.cya.util.ro.BookPageIn;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

/**
 * @Description 借阅管理
 * @Date 2022/9/4 16:35
 *
 */
@Service
public class BorrowService {

    @Autowired
    private BorrowRepository borrowRepository;

    @Autowired
    private BorrowMapper borrowMapper;

    @Autowired
    private BookService bookService;

    @Autowired
    private UserService userService;

    /**
         * 添加
     */
    @Transactional
    public Integer addBorrow(Borrow borrow) {
        Book book = bookService.findBook(borrow.getBookId());
        //Users users = userService.findUserById(borrow.getUserId());

        // 查询是否已经借阅过该图书
        Borrow bor = findBorrowByUserIdAndBookId(borrow.getNumber(),book.getId());
        if (bor!=null) {
            Integer ret = bor.getRet();
            if (ret!=null) {
                // 已借阅, 未归还 不可再借
                if (ret == Constants.NO) {
                    return Constants.BOOK_BORROWED;
                }
            }
        }

        // 库存数量减一
        int size = book.getSize();
        if (size>0) {
            size--;
            book.setSize(size);
            bookService.updateBook(book);
        }else {
            return Constants.BOOK_SIZE_NOT_ENOUGH;
        }

        // 用户可借数量减一
        /*int userSize = users.getSize();
        if (userSize>0) {
            userSize --;
            users.setSize(userSize);
            userService.updateUser(users);
        }else {
            return Constants.USER_SIZE_NOT_ENOUGH;
        }*/


        // 添加借阅信息, 借阅默认为未归还状态
        borrow.setRet(Constants.NO);
        borrowRepository.saveAndFlush(borrow);

        // 一切正常
        return Constants.OK;
    }

    /**
     * user id查询所有借阅信息
     */
    public List<Borrow> findAllBorrowByUserId(Integer userId) {
        return borrowRepository.findBorrowByUserId(userId);
    }

    public List<Borrow> findAllBorrow(){
        return borrowRepository.findAll();
    }

    public List<Borrow> searchBorrow(String name,String isbn,String number){
        return borrowMapper.searchBorrow(name,isbn,number);
    }

    public List<Borrow> searchBorrow(BookPageIn pageIn,String status){
        if (null!=status && !status.trim().equals("") && "1".equals(status)){
            return borrowMapper.searchBackBorrow(pageIn.getName(),pageIn.getIsbn(),pageIn.getNumber());
        }
        return borrowMapper.searchBorrow(pageIn.getName(),pageIn.getIsbn(),pageIn.getNumber());
    }

    /**
     * user id查询所有 已借阅信息
     */
    public List<Borrow> findBorrowsByUserIdAndRet(Integer userId, Integer ret) {
        return borrowRepository.findBorrowsByUserIdAndRet(userId,ret);
    }


    /**
         * 详情
     */
    public Borrow findById(Integer id) {
        Optional<Borrow> optional = borrowRepository.findById(id);
        if (optional.isPresent()) {
            return optional.get();
        }
        return null;
    }

    /**
         * 编辑
     */
    public boolean updateBorrow(Borrow borrow) {
        return borrowMapper.updateBorrow(borrow)>0;
    }


    /**
         * 编辑
     */
    public Borrow updateBorrowByRepo(Borrow borrow) {
        return borrowRepository.saveAndFlush(borrow);
    }

    /**
         * 根据ID删除
     */
    public void deleteBorrow(Integer id) {
        borrowRepository.deleteById(id);
    }
    
    /**
         * 根据book_id删除
     */
    public void deleteBorrowByBookId(Integer bookId) {
    	borrowMapper.deleteBorrowByBookId(bookId);
    }
    

    /**
         * 查询用户某一条借阅信息
     * @param userId 用户id
     * @param bookId 图书id
     */
    public Borrow findBorrowByUserIdAndBookId(String userId,int bookId) {
        return borrowMapper.findBorrowByUserIdAndBookId(userId,bookId);
    }

    public Borrow findBorrowByUserIdAndBookId2(String number,int bookId) {
        return borrowMapper.findBorrowByUserIdAndBookId2(number,bookId);
    }

    /**
         * 归还书籍,
     * @param userId 用户Id
     * @param bookId 书籍id
     */
    @Transactional(rollbackFor = Exception.class)
    public void retBook(int userId,int bookId) {
        // 用户可借数量加1
        Users user = userService.findUserById(userId);
        Integer size = user.getSize();
        size++;
        user.setSize(size);
        userService.updateUser(user);


        // 书籍库存加1
        Book book = bookService.findBook(bookId);
        Integer bookSize = book.getSize();
        bookSize++;
        book.setSize(bookSize);
        bookService.updateBook(book);
        // 借阅记录改为已归还,删除记录
        Borrow borrow = this.findBorrowByUserIdAndBookId("", bookId);
        this.deleteBorrow(borrow.getId());
    }

    @Transactional(rollbackFor = Exception.class)
    public void retBook2(String number,int bookId) {
        // 用户可借数量加1
       /* Users user = userService.findUserById(userId);
        Integer size = user.getSize();
        size++;
        user.setSize(size);
        userService.updateUser(user);*/


        // 书籍库存加1
        Book book = bookService.findBook(bookId);
        Integer bookSize = book.getSize();
        bookSize++;
        book.setSize(bookSize);
        bookService.updateBook(book);

        // 借阅记录改为已归还,删除记录
        //Borrow borrow = this.findBorrowByUserIdAndBookId(userId, bookId);
        Borrow borrow = this.findBorrowByUserIdAndBookId2(number, bookId);
        borrow.setStatus("已还");
        borrowMapper.updateBorrowStatus(borrow);
        //this.deleteBorrow(borrow.getId());
    }
}

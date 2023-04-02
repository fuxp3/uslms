package com.cya.controller;

import cn.hutool.core.date.DateUtil;
import com.cya.entity.Borrow;
import com.cya.service.BookService;
import com.cya.service.BorrowService;
import com.cya.util.R;
import com.cya.util.consts.Constants;
import com.cya.util.http.CodeEnum;
import com.cya.util.ro.BookPageIn;
import com.cya.util.vo.BackOut;
import com.cya.util.vo.BookOut;
import com.cya.util.vo.PageOut;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Description 用户管理
 */
@Api(tags = "借阅管理")
@RestController
@RequestMapping("/borrow")
public class BorrowController {

    @Autowired
    private BorrowService borrowService;

    @Autowired
    private BookService bookService;

    @ApiOperation("借阅列表")
    @GetMapping("/list")
    public R getBorrowList(Integer userId) {
        return R.success(CodeEnum.SUCCESS,borrowService.findAllBorrowByUserId(userId));
    }

    @ApiOperation("借阅图书")
    @PostMapping("/add")
    public R addBorrow(@RequestBody Borrow borrow) {
        Integer result = borrowService.addBorrow(borrow);
        if (result == Constants.BOOK_BORROWED) {
            return R.success(CodeEnum.BOOK_BORROWED);
        }else if (result == Constants.USER_SIZE_NOT_ENOUGH) {
            return R.success(CodeEnum.USER_NOT_ENOUGH);
        }else if (result == Constants.BOOK_SIZE_NOT_ENOUGH) {
            return R.success(CodeEnum.BOOK_NOT_ENOUGH);
        }
        return R.success(CodeEnum.SUCCESS,Constants.OK);
    }

    @ApiOperation("编辑借阅")
    @PostMapping("/update")
    public R modifyBorrow(@RequestBody Borrow borrow) {
        return R.success(CodeEnum.SUCCESS,borrowService.updateBorrow(borrow));
    }


    @ApiOperation("借阅详情")
    @GetMapping("/detail")
    public R borrowDetail(Integer id) {
        return R.success(CodeEnum.SUCCESS,borrowService.findById(id));
    }

    @ApiOperation("删除归还记录")
    @GetMapping("/delete")
    public R delBorrow(Integer id) {
        borrowService.deleteBorrow(id);
        return R.success(CodeEnum.SUCCESS);
    }


    @ApiOperation("已借阅列表")
    @PostMapping("/borrowed")
    public R borrowedList(@RequestBody BookPageIn pageIn,Integer userId, String name, String isbn, String number,@RequestParam(required = false,name = "status") String status) {
        if (pageIn == null) {
            return R.fail(CodeEnum.PARAM_ERROR);
        }

        PageHelper.startPage(pageIn.getCurrPage(),pageIn.getPageSize());
        List<Borrow> borrows = borrowService.searchBorrow(pageIn,status);
        PageInfo<Borrow> pageInfo = new PageInfo<>(borrows);


        List<BackOut> outs = new ArrayList<>();
        // 获取所有 已借阅 未归还书籍
        //List<Borrow> borrows = borrowService.findBorrowsByUserIdAndRet(userId, Constants.NO);

        //List<Borrow> borrows = borrowService.findAllBorrow();

        for (Borrow borrow : pageInfo.getList()) {
            BackOut backOut = new BackOut();
            BookOut out = bookService.findBookById(borrow.getBookId());
            BeanUtils.copyProperties(out,backOut);

            backOut.setBorrowTime(DateUtil.format(borrow.getCreateTime(),Constants.DATE_FORMAT));

            String endTimeStr = DateUtil.format(borrow.getEndTime(), Constants.DATE_FORMAT);
            backOut.setEndTime(endTimeStr);
            // 判断是否逾期
            String toDay = DateUtil.format(new Date(), Constants.DATE_FORMAT);
            int i = toDay.compareTo(endTimeStr);
            if (i>0) {
                backOut.setLate(Constants.YES_STR);
            }else {
                backOut.setLate(Constants.NO_STR);
            }

            backOut.setNumber(borrow.getNumber());

            outs.add(backOut);
        }
        // 自定义分页返回对象
        PageOut pageOut = new PageOut();
        pageOut.setList(outs);
        pageOut.setTotal((int)pageInfo.getTotal());
        pageOut.setCurrPage(pageInfo.getPageNum());
        pageOut.setPageSize(pageInfo.getPageSize());

        return R.success(CodeEnum.SUCCESS,pageOut);
    }

    @ApiOperation("归还书籍")
    @PostMapping("/ret")
    public R retBook(String number, Integer bookId) {
        // 归还图书
        borrowService.retBook2(number,bookId);
        return R.success(CodeEnum.SUCCESS);
    }

}

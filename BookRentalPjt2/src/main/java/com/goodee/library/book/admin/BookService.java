package com.goodee.library.book.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.library.book.BookVo;
import com.goodee.library.book.HopeBookVo;
import com.goodee.library.book.RentalBookVo;

@Service
public class BookService {
	final static public int BOOK_ISBN_ALEADY_EXIST = 0;
	final static public int BOOK_REGISTER_SUCCESS = 1;
	final static public int BOOK_REGISTER_FAIL = -1;
	
	@Autowired
	BookDao bookDao;
	
	public int registerBookConfirm(BookVo bookVo) {
		System.out.println("[BookService] registerBookConfirm()");
		
		boolean isISBN = bookDao.isISBN(bookVo.getB_isbn());
		
		if(!isISBN) {
			int result = bookDao.insertBook(bookVo);
			
			if(result >0)
				return BOOK_REGISTER_SUCCESS;
			else 
				return BOOK_REGISTER_FAIL;
		}else {
			return BOOK_ISBN_ALEADY_EXIST;
		}
	}
	
	public List<BookVo> searchBookConfirm(BookVo bookVo){
		System.out.println("[BookService] searchBookConfirm()");
		
		return bookDao.selectBooksBySearch(bookVo);
	}
	
	public BookVo bookDetail(int b_no) {
		System.out.println("[BookService] bookDetail()");
		
		return bookDao.selectBook(b_no);
	}
	
	public BookVo modifyBookForm(int b_no) {
		System.out.println("[BookService] modifyBookForm()");
		
		return bookDao.selectBook(b_no);
	}
	
	public int modifyBookConfirm(BookVo bookVo) {
		
		System.out.println("[BookService] modifyBookConfirm()");
		
		return bookDao.updateBook(bookVo);
	}
		
	//도서 삭제 확인
	public int deleteBookConfirm(int b_no) {
		
		System.out.println("[BookService] deleteBookConfirm()");
		
		return bookDao.deleteBook(b_no);
	}
	
	// 대출도서 목록 서비스
	public List<RentalBookVo> getRentalBooks(){
		System.out.println("[BookService] getRentalBooks()");
		
		return bookDao.selectRentalBooks();
	}
	
	
	public int returnBookConfirm(int b_no, int rb_no) {
		
		System.out.println("[BookService] returnBookConfirm()");
		
		int result = bookDao.updateRentalBook(rb_no);
		
		if(result > 0)
			result = bookDao.updateBook(b_no);
		
		return result;
	}
	
	
	public List<HopeBookVo> getHopeBooks(){
		System.out.println("[BookService] getHopeBooks()");
		
		return bookDao.selectHopeBooks();
	}
	
	public int registerBookConfirm(BookVo bookVo,int hb_no) {
		System.out.println("[BookService] registerBookConfirm()");
		
		boolean isISBN = bookDao.isISBN(bookVo.getB_isbn());
		
		if(!isISBN) {
			int result = bookDao.insertBook(bookVo);
			
			if(result > 0) {
				bookDao.updateHopeBookResult(hb_no);
				
				return BOOK_REGISTER_SUCCESS;
			}else {
				return BOOK_REGISTER_FAIL;
			}
		}else {
				return BOOK_ISBN_ALEADY_EXIST;
		}
	}
	
	public List<BookVo> getAllBooks(){
		System.out.println("[BookService] getAllBooks()");
		
		return bookDao.selectAllBooks();
	}
}

package com.goodee.library.book.user;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.goodee.library.book.BookVo;
import com.goodee.library.book.HopeBookVo;
import com.goodee.library.book.RentalBookVo;

@Service
public class BookService {
	
	@Autowired
	BookDao bookDao;
	
	public List<BookVo> searchBookConfirm(BookVo bookVo){
		System.out.println("[BookService] searchBookConfirm()");
		
		return bookDao.selectBookBySearch(bookVo);
	}
	
	public BookVo bookDetail(int b_no) {
		System.out.println("[BookService] bookDetail()");
		
		return bookDao.selectBook(b_no);
	}
	
	public int rentalBookConfirm(int b_no,int u_m_no) {
		System.out.println("[BookService] rentalBookConfirm()");
		
		int result = bookDao.insertRentalBook(b_no,u_m_no);
		
		if(result >= 0)
			bookDao.updateRentalBookAble(b_no);

		return result;
	}
	
	// 대여중인 도서 목록
	public List<RentalBookVo> enterBookshelf(int u_m_no){
		
		System.out.println("[BookService] enterBookshelf()");
		
		return bookDao.selectRentalBooks(u_m_no);
		
	}
	
	// 전체 대출 이력 조회
	public List<RentalBookVo> listupRentalBookHistory(int u_m_no){
		System.out.println("[UserBookService] listupRentalBookHistroy()");
		
		return bookDao.selectRentalBookHistory(u_m_no);
	}
	
	// 희망도서 요청
	public int requestHopeBookConfirm(HopeBookVo hopeBookVo) {
		System.out.println("[BookService] requestHopeBookConfirm");
		
		return bookDao.insertHopeBook(hopeBookVo);
	}
	

    // 희망도서 요청 목록 조회
	public List<HopeBookVo> listupRequestHopeBook(int u_m_no){
		System.out.println("[BookService] listupRequestHopeBook()");
		
		return bookDao.selectRequestHopeBooks(u_m_no);
	}
}

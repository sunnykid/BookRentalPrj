package com.goodee.library.book.user;

import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.goodee.library.book.BookVo;
import com.goodee.library.book.HopeBookVo;
import com.goodee.library.book.RentalBookVo;
import com.goodee.library.user.member.UserMemberVo;

@Controller
@RequestMapping("/book/user")
public class BookController {

	@Autowired
	BookService bookService;
	
	// 도서 검색
	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(BookVo bookVo,Model model) {
		System.out.println("[BookController] searchBookConfirm()");
		
		String nextPage = "user/book/search_book";
		
		List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);
		
		model.addAttribute("bookVos", bookVos);
		
		return nextPage;
	}
	
	// 도서 상세
	@GetMapping("bookDetail")
	public String bookDetail(@RequestParam("b_no") int b_no,Model model) {
		System.out.println("[BookController] bookDetail");
		
		String nextPage = "user/book/book_detail";
		
		BookVo bookVo = bookService.bookDetail(b_no);
		
		model.addAttribute("bookVo", bookVo);
		
		return nextPage;
	}
	
	// 도서 대여
	@GetMapping("/rentalBookConfirm")
	public String rentalBookConfirm(@RequestParam("b_no") int b_no, HttpSession session) {
		System.out.println("[BookController] rentalBookConfirm()");
		
		String nextPage = "user/book/rental_book_ok";
		
		UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
//		if(loginedUserMemberVo == null) {
//			return "redirect:/user/member/loginForm";
//		}
		
		int result = bookService.rentalBookConfirm(b_no,loginedUserMemberVo.getU_m_no());
		
		if(result <= 0)
			nextPage = "user/book/rental_book_ng";
		
		return nextPage;
	}
	
	// 나의 책장: 대여중인 도서 목록
	@GetMapping("/enterBookshelf")
	public String enterBookshelf(HttpSession session, Model model) {
		System.out.println("[BookController] enterBookshelf()");
		
		String nextPage = "user/book/bookshelf";
		
		UserMemberVo loginedUserMemberVo =
				(UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
		List<RentalBookVo> rentalBookVos =
				bookService.enterBookshelf(loginedUserMemberVo.getU_m_no());
		
		model.addAttribute("rentalBookVos", rentalBookVos);
		
		return nextPage;
	}
	
	// 전체 대출 이력
	@GetMapping("/listupRentalBookHistory")
	public String listupRentalBookHistory(HttpSession session, Model model) {
		System.out.println("[UserBookController] listupRentalBookHistory()");
		
		String nextPage = "user/book/rental_book_history";
		
		UserMemberVo loginedUserMemberVo =
				(UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
		List<RentalBookVo> rentalBookVos = 
				bookService.listupRentalBookHistory(loginedUserMemberVo.getU_m_no());
		
		model.addAttribute("rentalBookVos", rentalBookVos);
		
		return nextPage;
	}
	
	// 희망도서 요청
	@GetMapping("/requestHopeBookForm")
	public String requestHopeBookForm() {
		System.out.println("[UserBookController] requestHopeBookForm()");
		
		String nextPage = "user/book/request_hope_book_form";
		
		return nextPage;
	}

	// 희망도서 요청 확인
	@GetMapping("/requestHopeBookConfirm")
	public String requestHopeBookConfirm(HopeBookVo hopeBookVo, HttpSession session) {
		System.out.println("[UserBookController] requestHopeBookConfirm");
		
		String nextPage = "user/book/request_hope_book_ok";
		
		UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
		hopeBookVo.setU_m_no(loginedUserMemberVo.getU_m_no());
		
		int result = bookService.requestHopeBookConfirm(hopeBookVo);
		
		if(result <= 0)
			nextPage = "user/book/request_hope_book_ng";
		
		return nextPage;
	}
	
	//희망도서 요청목록
	@GetMapping("/listupRequestHopeBook")
	public String listupRequestBook(HttpSession session,Model model) {
		System.out.println("[UserBookController] listupRequestHopeBook()");
		
		String nextPage = "user/book/list_hope_book";
		
		UserMemberVo loginedUserMemberVo = (UserMemberVo) session.getAttribute("loginedUserMemberVo");
		
		List<HopeBookVo> hopeBookVos = bookService.listupRequestHopeBook(loginedUserMemberVo.getU_m_no());
		
		model.addAttribute("hopeBookVos", hopeBookVos);
		
		return nextPage;
	}
	

}

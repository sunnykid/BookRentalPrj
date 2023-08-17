package com.goodee.library.book.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.goodee.library.book.BookVo;
import com.goodee.library.book.HopeBookVo;
import com.goodee.library.book.RentalBookVo;
import com.goodee.library.book.admin.util.UploadFileService;

@Controller
@RequestMapping("/book/admin")
public class BookController {

	@Autowired
	BookService bookService;
	
	@Autowired
	UploadFileService uploadFileService;
	
	// 도서 등록
	@GetMapping("/registerBookForm")
	public String registerBookForm() {
		System.out.println("[BookController] registerBookForm()");
		
		String nextPage = "admin/book/register_book_form";
		
		return nextPage;
	}
	
	// 도서 등록 확인
	@PostMapping("/registerBookConfirm")
	public String registerBookConfirm(BookVo bookVo,
									@RequestParam("file") MultipartFile file) {
		
		System.out.println("[BookController] registerBookConfirm()");
		
		String nextPage = "admin/book/register_book_ok";
		
		// 파일 저장
		String savedFileName = uploadFileService.upload(file);
		
		if (savedFileName != null) {
			bookVo.setB_thumbnail(savedFileName);
			int result = bookService.registerBookConfirm(bookVo);
			
			if(result <= 0)
				nextPage = "admin/book/register_book_ng";
		}else
			nextPage = "admin/book/register_book_ng";
		
		return nextPage;
	}
	
	// 도서 검색
	@GetMapping("/searchBookConfirm")
	public String searchBookConfirm(BookVo bookVo,Model model) {
		System.out.println("[BookController] searchBookConfirm()");
		
		String nextPage = "admin/book/search_book";
		
		List<BookVo> bookVos = bookService.searchBookConfirm(bookVo);
		
		model.addAttribute("bookVos", bookVos);
		
		return nextPage;
	}
	
	// 도서 상제
	@GetMapping("/bookDetail")
	public String bookDetail(@RequestParam("b_no") int b_no,Model model) {
		System.out.println("[BookContoller] bookDetail()");
		
		String nextPage = "admin/book/book_detail";
		
		BookVo bookVo = bookService.bookDetail(b_no);
		
		model.addAttribute("bookVo", bookVo);
		
		return nextPage;
	}
	
	// 도서 수정
	@GetMapping("/modifyBookForm")
	public String modifyBookForm(@RequestParam("b_no") int b_no,Model model) {
	
		System.out.println("[BookController] modifyBookForm()");
		
		BookVo bookVo = bookService.modifyBookForm(b_no);
		
		model.addAttribute("bookVo", bookVo);
		
		String nextPage = "admin/book/modify_book_form";
		
		return nextPage; 
	}
	
	// 도서 수정 확인
	@PostMapping("/modifyBookConfirm")
	public String modifyBookConfirm(BookVo bookVo,@RequestParam("file") MultipartFile file) {
		
		System.out.println("[BookController] modifyBookConfirm()");
		
		String nextPage = "admin/book/modify_book_ok";

		if(!file.getOriginalFilename().equals("")) {
			// 파일 저장
			String savedFileName = uploadFileService.upload(file);
			if(savedFileName != null) {
				bookVo.setB_thumbnail(savedFileName);
			}
		}
		
		int result = bookService.modifyBookConfirm(bookVo);
		
		if(result < 0) {
			nextPage = "admin/book/modify_book_ng";
		}
		
		return nextPage;
	}
	
	// 도서 삭제
	@GetMapping("/deleteBookConfirm")
	public String deleteBookConfirm(@RequestParam("b_no") int b_no) {
		System.out.println("[BookController] deleteBookConfirm()");
		
		String nextPage = "admin/book/delete_book_ok";
		
		int result = bookService.deleteBookConfirm(b_no);
		
		if(result <= 0) {
			nextPage = "admin/book/delete_book_ng";
		}
		
		return nextPage;
	}

	// 대출 도서 목록
	@GetMapping("/getRentalBooks")
	public String getRentalBooks(Model model) {
		System.out.println("[BookController] getRentalBooks");
		
		String nextPage = "admin/book/rental_books";
		
		List<RentalBookVo> rentalBookVos = bookService.getRentalBooks();
		
		model.addAttribute("rentalBookVos", rentalBookVos);
		
		return nextPage;
	}
	
	// 도서 반납 확인
	@GetMapping("/returnBookConfirm")
	public String rentalBookConfirm(@RequestParam("b_no") int b_no,
									@RequestParam("rb_no") int rb_no) {
		System.out.println("[BookController] rentalBookConfirm()");
		
		String nextPage = "admin/book/return_book_ok";
		
		int result = bookService.returnBookConfirm(b_no,rb_no);
		
		if(result <=0)
			nextPage = "admin/book/return_book_ng";
		return nextPage;
	}
	
	// 희망도서 목록
	@GetMapping("/getHopeBooks")
	public String getHopeBooks(Model model) {
		System.out.println("[AdminBookController] getHopeBooks()");
		
		String nextPage = "admin/book/hope_books";
		
		List<HopeBookVo> hopeBookVos = bookService.getHopeBooks();
		
		model.addAttribute("hopeBookVos", hopeBookVos);
		
		return nextPage;
	}
	
	// 희망 도서 등록(입고처리)
	@GetMapping("/registerHopeBookForm")
	public String registerHopeBookForm(Model model, HopeBookVo hopeBookVo) {
		System.out.println("[AdminBookController] registerHopeBookForm()");
		
		String nextPage = "admin/book/register_hope_book_form";
		
		model.addAttribute("hopeBookVo", hopeBookVo);
		
		return nextPage;
	}
	
	// 희망 도서등록(입고처리) 확인
	@PostMapping("registerHopeBookConfirm")
	public String registerHopeBookConfirm(BookVo bookVo,
											@RequestParam("hb_no") int hb_no,
											@RequestParam("file") MultipartFile file) {

		System.out.println("[AdminBookController] registerHopeBookConfirm()");
		
		String nextPage = "admin/book/register_book_ok";
		
		String savedFileName = uploadFileService.upload(file);
		
		if(savedFileName != null) {
			bookVo.setB_thumbnail(savedFileName);
			int result = bookService.registerBookConfirm(bookVo,hb_no);
			
			if(result <=0)
				nextPage = "admin/book/register_book_ng";
		}else {
			nextPage = "admin/book/register_book_ng";
		}
		
		return nextPage;
	}
	
	// 전체 도서 목록
	@GetMapping("/getAllBooks")
	public String getAllBooks(Model model) {
		System.out.println("[AdminBookController] getAllBooks()");
		
		String nextPage = "admin/book/full_list_of_books";
		
		List<BookVo> bookVos = bookService.getAllBooks();
		
		model.addAttribute("bookVos", bookVos);
		
		return nextPage;
	}
}






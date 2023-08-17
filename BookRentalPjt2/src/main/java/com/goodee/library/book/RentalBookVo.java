package com.goodee.library.book;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RentalBookVo {
	
	private int    rb_no			;
	private String rb_start_date	;
	private String rb_end_date		;
	private String rb_reg_date		;
	private String rb_mod_date		;
	
	private int    b_no                   ;
	private String b_thumbnail            ;
	private String b_name				  ;
	private String b_author               ;
	private String b_publisher            ;
	private String b_publish_year         ;
	private String b_isbn                 ;
	private String b_call_number          ;
	private int    b_rental_able          ;
	private String b_reg_date             ;
	private String b_mod_date             ;

	private int    u_m_no                 ;
	private String u_m_id                 ;
	private String u_m_pw                 ;
	private String u_m_name               ;
	private String u_m_gender             ;
	private String u_m_mail               ;
	private String u_m_phone              ;
	private String u_m_reg_date           ;
	private String u_m_mod_date           ;
}

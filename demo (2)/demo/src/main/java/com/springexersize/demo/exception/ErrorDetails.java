package com.springexersize.demo.exception;

import lombok.*;

import java.util.Date;
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ErrorDetails {
    private String message;
    private Date date;
    private String url;
}

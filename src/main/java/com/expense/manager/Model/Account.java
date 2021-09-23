package com.expense.manager.Model;

import com.sun.istack.NotNull;

import javax.persistence.*;
import javax.validation.constraints.Size;

@Entity
@Table(name = "accounts")
public class Account {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    @NotNull
    @Size(min = 5, max = 100, message = "Title length should be between 5 and 100")
    private String title;

    @NotNull
    @Size(min = 5, max = 255, message = "Description length should be between 5 and 255")
    private String description;
}


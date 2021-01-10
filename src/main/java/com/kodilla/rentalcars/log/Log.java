package com.kodilla.rentalcars.log;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Entity
@Table(name = "LOGS")
public class Log {
    private Long id;
    private String log;
    private LocalDate date;

    public Log(String log, LocalDate date) {
        this.log = log;
        this.date = date;
    }

    @Id
    @NotNull
    @GeneratedValue
    @Column(name = "ID")
    public Long getId() {
        return id;
    }

    @Column(name = "LOG")
    public String getLog(){return log;}

    @Column(name = "DATE")
    public LocalDate getDate() {
        return date;
    }
}

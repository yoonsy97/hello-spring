package hello.hellospring.domain;

import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
public class Board extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column
    private String title;



    @Column
    private String content;

    @Column
    private Long writer;




    public Long getBno() {
        return bno;
    }

    public void setBoardid(Long boardid) {
        this.bno = boardid;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Long getWriter() {
        return writer;
    }

    public void setWriter(Long writer) {
        this.writer = writer;
    }


  }

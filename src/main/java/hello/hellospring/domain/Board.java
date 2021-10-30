package hello.hellospring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;

import javax.persistence.Entity;
import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@Table(name="board")
@NoArgsConstructor
public class Board extends BaseTimeEntity{

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long bno;

    @Column
    private String title;

    @Column
    private String content;

    @ManyToOne
    @JoinColumn(name="writer")
    private Member writer;

    @Builder
    private Board(String title, String content, Member writer){
        this.title=title;
        this.content=content;
        this.writer=writer;


    }



  }

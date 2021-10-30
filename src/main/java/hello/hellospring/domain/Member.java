package hello.hellospring.domain;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.*;

@Getter
@Entity
@Table(name="member")
@NoArgsConstructor
public class Member {

    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;//시스템이 저장하는 아이

    @Column(name="userid")
    private String userid;
    @Column(name="userpassword")
    private String userpassword;
    @Column(name="level")
    private int level;

    @OneToMany(mappedBy="writer", fetch=FetchType.EAGER)
    private List<Board> boardList=new ArrayList<Board>();


    @Builder
    public Member(String userid, String userpassword, int level) {
        this.userid = userid;
        this.userpassword=userpassword;
        this.level=level;
    }


}

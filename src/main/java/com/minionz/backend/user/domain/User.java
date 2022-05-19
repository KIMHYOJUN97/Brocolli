package com.minionz.backend.user.domain;

import com.minionz.backend.calendar.domain.Calendar;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AttributeOverrides({
        @AttributeOverride(name = "id", column = @Column(name = "user_id")),
        @AttributeOverride(name = "name", column = @Column(name = "user_name"))
})
@Entity
public class User extends UserBaseEntity {

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    private List<Calendar> calendarList = new ArrayList<>();

    @Column(nullable = false)
    private String age;

    @Builder
    public User(Long id,String name, String email, String password,String weight, String height, String gender,String age) {
        super(id,name, email, password,weight,height,gender);
        this.age =age;
    }
}

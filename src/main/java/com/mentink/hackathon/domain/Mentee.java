package com.mentink.hackathon.domain;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import javax.transaction.Transactional;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@NoArgsConstructor
@Setter
@Getter
@Entity
public class Mentee extends BaseTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String userName;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false)
    private String password;

    @Builder
    public Mentee(String userName, String email ,String password) {
        this.userName = userName;
        this.email = email;
        this.password = password;

    }
    @ElementCollection(fetch = FetchType.EAGER)
    private Set<MenteeRole> roleSet = new HashSet<>();


    @JsonIgnore
    @OneToMany(mappedBy = "mentee",fetch = FetchType.LAZY)
    private List<Matching> matchingList;

    public void addMemberRole(MenteeRole menteeRole){
        roleSet.add(menteeRole);
    }


}

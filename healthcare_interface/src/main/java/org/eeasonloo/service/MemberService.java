package org.eeasonloo.service;

import org.eeasonloo.pojo.Member;

public interface MemberService {

    public void add(Member member);
    public Member findByEmail(String email);
}

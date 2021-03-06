package hello.core.order;

import hello.core.annotaion.MainDiscountPolicy;
import hello.core.discount.DiscountPolicy;
import hello.core.member.Member;
import hello.core.member.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;


@Component
//@RequiredArgsConstructor //final 붙은 필드를 받는 생성자를 만듦
public class OrderServiceImpl implements OrderService{

    private final MemberRepository memberRepository;


    private final DiscountPolicy discountPolicy; //이렇게하면 인터페이스에만 의존함.
//private final DiscountPolicy discountPolicy = new RateDiscountPolicy();

    @Autowired
    public OrderServiceImpl(MemberRepository memberRepository, @MainDiscountPolicy DiscountPolicy discountPolicy) {
        this.memberRepository = memberRepository;
        this.discountPolicy = discountPolicy;
    }

    @Override
    public Order createOrder(Long memberId, String itemName, int itemPrice) {

        Member member = memberRepository.findById(memberId);

        //OrderService : 할인금액 나는 몰라, discountPolicy 니가 내놔
        //discountPolicy : 네
        int discountPrice = discountPolicy.discount(member, itemPrice);

        return new Order(memberId, itemName, itemPrice, discountPrice);


    }

    //테스트 용도
    public MemberRepository getMemberRepository(){
        return memberRepository;
    }


}

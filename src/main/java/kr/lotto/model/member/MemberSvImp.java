package kr.lotto.model.member;

import kr.lotto.model.Paging;
import kr.lotto.model.PagingList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.Optional;

@Service
public class MemberSvImp implements MemberSv {
    @Autowired MemberRepository memberRepository;
    //하나 가져오기
    @Override
    public Optional<MemberData> getMember(String memberId)
	{
        return Optional.ofNullable (memberRepository.getMember(memberId));
    }

    /**
     * 회원중복체크
     * @param memberId 회원아이디
     * @return 아이디가 있다면 true
     *          아이디가 없다면 false
     */
    @Override
    public boolean existMemberId(String memberId) {
        return memberRepository.getCountMemberId(memberId) > 0;
    }

    /**
     *  회원을 등록한다
     * @param memberData 신규회원 정보
     */
    @Override
    public void addMember(MemberData memberData)
    {
		memberRepository.addMember(memberData);
    }

    /**
     *  회원을 삭제한다
     * @param memberId 삭제할 아이디
     */
    @Override
    public void deleteMember(String memberId){
        memberRepository.deleteMember(memberId);
    }

    /**
     * 회원정보를 수정한다
     * @param memberData 수정된 회원정보
     */
    @Override
    public void modifyMember(MemberData memberData){
        memberRepository.modifyMember(memberData);
    }

    /**
     * 회원의 전체 수를 구한다
     * @return 전체수 정수
     */
    @Override
    public int getCount(){
        return memberRepository.getCount();
    }

    /**
     * 회원 검색
     * @param paging 페이징 객체
     * @param searchData 검색 데이터
     */
	@Override
    public PagingList<MemberData> searchMember (Paging paging, MemberSearchData searchData)
	{
		paging.setSearchData (searchData.makeMap ());

		// 검색된 데이터의 총 데이터수
		paging.setTotalArticles (memberRepository.searchMemberCnt (paging.makeCntMap ()));

        return new PagingList<> (memberRepository.searchMember (paging.makeMap ()), paging);
    }
}

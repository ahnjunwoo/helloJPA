package hello.jpa;

import hello.jpa.entity.Member;
import hello.jpa.entity.MemberType;
import hello.jpa.entity.Team;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

public class Main {

    public static void main(String[] args) {
        EntityManagerFactory emf = Persistence.createEntityManagerFactory("hello");

        EntityManager em = emf.createEntityManager();

        EntityTransaction tx = em.getTransaction();
        tx.begin();
        try {
            Team team = new Team();
            team.setName("tigers");
            em.persist(team);
            Member member = new Member();
            member.setName("ahn");
            member.setMemberType(MemberType.USER);
            member.setAge(10);
            team.getMembers().add(member);
            member.setTeam(team);
            em.persist(member);
            //
            em.flush();
            em.clear();

           /* Member findMember = em.find(Member.class, member.getId());
            System.out.println("team id :"+findMember.getTeam().getId());
            List<Member> members = findMember.getTeam().getMembers();
            for (Member member1 : members) {
                System.out.println("member1 = " + member1.getName());
            }*/
            tx.commit();
        } catch (Exception e) {
          tx.rollback();
        } finally {
            em.close();
        }


        System.out.println("hello");
        emf.close();
    }
}

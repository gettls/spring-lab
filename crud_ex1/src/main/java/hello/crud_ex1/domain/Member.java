
package hello.crud_ex1.domain;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.annotations.BatchSize;

import lombok.Getter;
import lombok.Setter;

@Entity
@Getter @Setter
public class Member {
	@Id @GeneratedValue
	@Column(name="MEMBER_ID")
	private Long id;
	
	private String name;
	private String loginId;
	private String password;
	
	@BatchSize(size=100)
	@OneToMany(mappedBy = "member")
	private List<Schedule> schdules = new ArrayList<Schedule>();
}

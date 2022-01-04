package lab.board.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.persistence.Column;
import javax.persistence.EntityListeners;
import javax.persistence.MappedSuperclass;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import lombok.Getter;

@Getter
@MappedSuperclass
@EntityListeners({AuditingEntityListener.class})
public class BaseEntity {
	
	@CreatedDate
    @Column(updatable = false)
    private String createTime;
    @LastModifiedDate
    private String modifiedTime;
    
    @CreatedBy
    @Column(updatable = false)
    private String createdBy;
    
    @PrePersist
    public void onPrePersist() {
    	this.createTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm:ss"));
    	this.modifiedTime = this.createTime;
    }
    @PreUpdate
    public void onPreUpdate() {
    	this.modifiedTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy년MM월dd일 HH:mm:ss"));
    }
}

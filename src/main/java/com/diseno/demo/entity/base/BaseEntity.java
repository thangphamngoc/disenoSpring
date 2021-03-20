package com.diseno.demo.entity.base;

import com.diseno.demo.common.AppConstant;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.CreatedBy;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedBy;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import javax.persistence.*;
import java.time.LocalDateTime;

/**
 * date 2021-03-19 08:50
 *
 * @author Phạm Ngọc Thắng
 */

@MappedSuperclass
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity<T> {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //id người tạo, lấy tự động
    @Column(nullable = false)
    @CreatedBy
    private Long idCreated;

    //tên người tạo, người dùng tự thêm vào
    private String createdBy;

    // thời gian tạo, lấy tự động
    @CreatedDate
    @Column(nullable = false)
    private LocalDateTime createdDate;

    //id người sửa. lấy tự động
    @LastModifiedBy
    private Long idModified;

    //tên người tạo, người code tự thêm nếu muốn
    private T lastModifieBy;

    // thời gian sửa, lấy tự động. thời gian sửa sẽ trùng với thời gian tạo trong trường hợp tạo
    @LastModifiedDate
    private LocalDateTime lastModifieDate;

    //1 là hiện thị ra, 0 là ko hiện thị ra
    @Column(nullable = false, columnDefinition = "bit default 1")
    private boolean delActive = AppConstant.APP_TRUE;
}

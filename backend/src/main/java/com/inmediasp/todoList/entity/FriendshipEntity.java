package com.inmediasp.todoList.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "friendship")
@IdClass(FriendshipIdEntity.class)
@AllArgsConstructor
@NoArgsConstructor
public class FriendshipEntity {
    @Id
    @Column(name = "user1_id")
    private Integer user1;

    @Id
    @Column(name = "user2_id")
    private Integer user2;

    public boolean equals(FriendshipEntity that) {
        if (this.user1.equals(that.user1) && this.user2.equals(that.user2)) {
            return true;
        }

        return this.user2.equals(that.user1) && this.user1.equals(that.user2);
    }
}

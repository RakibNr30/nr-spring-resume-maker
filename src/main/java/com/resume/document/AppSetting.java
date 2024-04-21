package com.resume.document;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;
import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.time.LocalDateTime;

@Getter @Setter
@Document(collection = "app_settings")
public class AppSetting {

    @Id
    private ObjectId id;

    @NotBlank
    @Field(name = "app_name")
    private String appName;

    @NotBlank
    @Field(name = "app_title")
    private String appTitle;

    @Field(name = "created_at")
    private LocalDateTime createdAt;

    @Field(name = "updated_at")
    private LocalDateTime updatedAt;
}

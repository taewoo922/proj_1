package org.example.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ArticleReply extends Dto {
    private  int articleId;
    private  int memberId;
    private  String body;
}
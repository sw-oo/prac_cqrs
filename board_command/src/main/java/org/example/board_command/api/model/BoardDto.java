package com.example.demo.board.model;

import com.example.demo.likes.model.LikesDto;
import com.example.demo.reply.model.ReplyDto;
import com.example.demo.user.model.User;
import lombok.*;

import java.util.List;

public class BoardDto {
    @Getter
    public static class RegReq {
        private String title;
        private String contents;

        public Board toEntity(User user) {
            return Board.builder()
                    .title(this.title)
                    .contents(this.contents)
                    .user(user)
                    .build();
        }
    }

    @Builder
    @Getter
    public static class RegRes {
        private Long idx;
        private String title;
        private String contents;
        private Long userIdx;

        public static RegRes from(Board entity) {
            return RegRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .userIdx(entity.getUser().getIdx())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ListRes {
        private Long idx;
        private String title;
        private String contents;
        private String writer;
        private List<ReplyDto.ReadRes> replyList;
        private List<LikesDto.ReadRes> likesList;

        public static ListRes from(Board entity) {
            return ListRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .writer(entity.getUser().getName())
                    .replyList(entity.getReplyList().stream().map(ReplyDto.ReadRes::from).toList())
                    .likesList(entity.getLikesList().stream().map(LikesDto.ReadRes::from).toList())
                    .build();
        }
    }

    @Builder
    @Getter
    public static class ReadRes {
        private Long idx;
        private String title;
        private String contents;
        private String writer;
        private List<ReplyDto.ListRes> replyList;
        private List<LikesDto.ReadRes> likesList;

        public static ReadRes from(Board entity) {
            return ReadRes.builder()
                    .idx(entity.getIdx())
                    .title(entity.getTitle())
                    .contents(entity.getContents())
                    .writer(entity.getUser().getName())
                    .replyList(entity.getReplyList().stream().map(ReplyDto.ListRes::from).toList())
                    .likesList(entity.getLikesList().stream().map(LikesDto.ReadRes::from).toList())
                    .build();
        }
    }
}

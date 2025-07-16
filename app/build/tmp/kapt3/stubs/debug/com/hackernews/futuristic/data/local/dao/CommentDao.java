package com.hackernews.futuristic.data.local.dao;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000,\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010 \n\u0002\u0010\t\n\u0002\b\u0005\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010\b\n\u0002\b\f\bg\u0018\u00002\u00020\u0001J\u001c\u0010\u0002\u001a\u00020\u00032\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0016\u0010\b\u001a\u00020\u00032\u0006\u0010\t\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0018\u0010\u000b\u001a\u0004\u0018\u00010\f2\u0006\u0010\r\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u000e\u0010\u000e\u001a\u00020\u000fH\u00a7@\u00a2\u0006\u0002\u0010\u0010J\"\u0010\u0011\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\f\u0010\u0012\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\"\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\f\u0010\u0004\u001a\b\u0012\u0004\u0012\u00020\u00060\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u001c\u0010\u0014\u001a\b\u0012\u0004\u0012\u00020\f0\u00052\u0006\u0010\u0015\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\nJ\u0016\u0010\u0016\u001a\u00020\u00032\u0006\u0010\u0017\u001a\u00020\fH\u00a7@\u00a2\u0006\u0002\u0010\u0018J\u001c\u0010\u0019\u001a\u00020\u00032\f\u0010\u001a\u001a\b\u0012\u0004\u0012\u00020\f0\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007\u00a8\u0006\u001b"}, d2 = {"Lcom/hackernews/futuristic/data/local/dao/CommentDao;", "", "deleteComments", "", "ids", "", "", "(Ljava/util/List;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "deleteOldComments", "olderThan", "(JLkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCommentById", "Lcom/hackernews/futuristic/data/local/entities/CommentEntity;", "id", "getCommentCount", "", "(Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getCommentTree", "rootCommentIds", "getCommentsByIds", "getCommentsByParent", "parentId", "insertComment", "comment", "(Lcom/hackernews/futuristic/data/local/entities/CommentEntity;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "insertComments", "comments", "app_debug"})
@androidx.room.Dao()
public abstract interface CommentDao {
    
    @androidx.room.Query(value = "SELECT * FROM comments WHERE id IN (:ids)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCommentsByIds(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> ids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hackernews.futuristic.data.local.entities.CommentEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM comments WHERE id = :id")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCommentById(long id, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super com.hackernews.futuristic.data.local.entities.CommentEntity> $completion);
    
    @androidx.room.Query(value = "SELECT * FROM comments WHERE parent = :parentId")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCommentsByParent(long parentId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hackernews.futuristic.data.local.entities.CommentEntity>> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertComments(@org.jetbrains.annotations.NotNull()
    java.util.List<com.hackernews.futuristic.data.local.entities.CommentEntity> comments, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Insert(onConflict = 1)
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object insertComment(@org.jetbrains.annotations.NotNull()
    com.hackernews.futuristic.data.local.entities.CommentEntity comment, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM comments WHERE fetchedAt < :olderThan")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteOldComments(long olderThan, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "DELETE FROM comments WHERE id IN (:ids)")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object deleteComments(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> ids, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @androidx.room.Query(value = "\n        WITH RECURSIVE comment_tree AS (\n            SELECT * FROM comments WHERE parent IS NULL AND id IN (:rootCommentIds)\n            UNION ALL\n            SELECT c.* FROM comments c\n            INNER JOIN comment_tree ct ON c.parent = ct.id\n        )\n        SELECT * FROM comment_tree ORDER BY time ASC\n    ")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCommentTree(@org.jetbrains.annotations.NotNull()
    java.util.List<java.lang.Long> rootCommentIds, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.hackernews.futuristic.data.local.entities.CommentEntity>> $completion);
    
    @androidx.room.Query(value = "SELECT COUNT(*) FROM comments")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getCommentCount(@org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.lang.Integer> $completion);
}
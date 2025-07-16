package com.hackernews.futuristic.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.hackernews.futuristic.data.local.entities.CommentEntity;
import com.hackernews.futuristic.data.local.entities.Converters;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class CommentDao_Impl implements CommentDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<CommentEntity> __insertionAdapterOfCommentEntity;

  private final Converters __converters = new Converters();

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldComments;

  public CommentDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfCommentEntity = new EntityInsertionAdapter<CommentEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `comments` (`id`,`by`,`time`,`text`,`kids`,`parent`,`type`,`fetchedAt`) VALUES (?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final CommentEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getBy() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getBy());
        }
        statement.bindLong(3, entity.getTime());
        if (entity.getText() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getText());
        }
        final String _tmp = __converters.fromLongList(entity.getKids());
        if (_tmp == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, _tmp);
        }
        if (entity.getParent() == null) {
          statement.bindNull(6);
        } else {
          statement.bindLong(6, entity.getParent());
        }
        if (entity.getType() == null) {
          statement.bindNull(7);
        } else {
          statement.bindString(7, entity.getType());
        }
        statement.bindLong(8, entity.getFetchedAt());
      }
    };
    this.__preparedStmtOfDeleteOldComments = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM comments WHERE fetchedAt < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertComments(final List<CommentEntity> comments,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCommentEntity.insert(comments);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertComment(final CommentEntity comment,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfCommentEntity.insert(comment);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldComments(final long olderThan,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldComments.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, olderThan);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteOldComments.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object getCommentsByIds(final List<Long> ids,
      final Continuation<? super List<CommentEntity>> $completion) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM comments WHERE id IN (");
    final int _inputSize = ids.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (Long _item : ids) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindLong(_argIndex, _item);
      }
      _argIndex++;
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CommentEntity>>() {
      @Override
      @NonNull
      public List<CommentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBy = CursorUtil.getColumnIndexOrThrow(_cursor, "by");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfKids = CursorUtil.getColumnIndexOrThrow(_cursor, "kids");
          final int _cursorIndexOfParent = CursorUtil.getColumnIndexOrThrow(_cursor, "parent");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final List<CommentEntity> _result = new ArrayList<CommentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CommentEntity _item_1;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpBy;
            if (_cursor.isNull(_cursorIndexOfBy)) {
              _tmpBy = null;
            } else {
              _tmpBy = _cursor.getString(_cursorIndexOfBy);
            }
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final List<Long> _tmpKids;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfKids)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfKids);
            }
            _tmpKids = __converters.toLongList(_tmp);
            final Long _tmpParent;
            if (_cursor.isNull(_cursorIndexOfParent)) {
              _tmpParent = null;
            } else {
              _tmpParent = _cursor.getLong(_cursorIndexOfParent);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            _item_1 = new CommentEntity(_tmpId,_tmpBy,_tmpTime,_tmpText,_tmpKids,_tmpParent,_tmpType,_tmpFetchedAt);
            _result.add(_item_1);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getCommentById(final long id,
      final Continuation<? super CommentEntity> $completion) {
    final String _sql = "SELECT * FROM comments WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<CommentEntity>() {
      @Override
      @Nullable
      public CommentEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBy = CursorUtil.getColumnIndexOrThrow(_cursor, "by");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfKids = CursorUtil.getColumnIndexOrThrow(_cursor, "kids");
          final int _cursorIndexOfParent = CursorUtil.getColumnIndexOrThrow(_cursor, "parent");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final CommentEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpBy;
            if (_cursor.isNull(_cursorIndexOfBy)) {
              _tmpBy = null;
            } else {
              _tmpBy = _cursor.getString(_cursorIndexOfBy);
            }
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final List<Long> _tmpKids;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfKids)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfKids);
            }
            _tmpKids = __converters.toLongList(_tmp);
            final Long _tmpParent;
            if (_cursor.isNull(_cursorIndexOfParent)) {
              _tmpParent = null;
            } else {
              _tmpParent = _cursor.getLong(_cursorIndexOfParent);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            _result = new CommentEntity(_tmpId,_tmpBy,_tmpTime,_tmpText,_tmpKids,_tmpParent,_tmpType,_tmpFetchedAt);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getCommentsByParent(final long parentId,
      final Continuation<? super List<CommentEntity>> $completion) {
    final String _sql = "SELECT * FROM comments WHERE parent = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, parentId);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CommentEntity>>() {
      @Override
      @NonNull
      public List<CommentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBy = CursorUtil.getColumnIndexOrThrow(_cursor, "by");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfKids = CursorUtil.getColumnIndexOrThrow(_cursor, "kids");
          final int _cursorIndexOfParent = CursorUtil.getColumnIndexOrThrow(_cursor, "parent");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final List<CommentEntity> _result = new ArrayList<CommentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CommentEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpBy;
            if (_cursor.isNull(_cursorIndexOfBy)) {
              _tmpBy = null;
            } else {
              _tmpBy = _cursor.getString(_cursorIndexOfBy);
            }
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final List<Long> _tmpKids;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfKids)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfKids);
            }
            _tmpKids = __converters.toLongList(_tmp);
            final Long _tmpParent;
            if (_cursor.isNull(_cursorIndexOfParent)) {
              _tmpParent = null;
            } else {
              _tmpParent = _cursor.getLong(_cursorIndexOfParent);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            _item = new CommentEntity(_tmpId,_tmpBy,_tmpTime,_tmpText,_tmpKids,_tmpParent,_tmpType,_tmpFetchedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getCommentTree(final List<Long> rootCommentIds,
      final Continuation<? super List<CommentEntity>> $completion) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("\n");
    _stringBuilder.append("        WITH RECURSIVE comment_tree AS (");
    _stringBuilder.append("\n");
    _stringBuilder.append("            SELECT * FROM comments WHERE parent IS NULL AND id IN (");
    final int _inputSize = rootCommentIds.size();
    StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
    _stringBuilder.append(")");
    _stringBuilder.append("\n");
    _stringBuilder.append("            UNION ALL");
    _stringBuilder.append("\n");
    _stringBuilder.append("            SELECT c.* FROM comments c");
    _stringBuilder.append("\n");
    _stringBuilder.append("            INNER JOIN comment_tree ct ON c.parent = ct.id");
    _stringBuilder.append("\n");
    _stringBuilder.append("        )");
    _stringBuilder.append("\n");
    _stringBuilder.append("        SELECT * FROM comment_tree ORDER BY time ASC");
    _stringBuilder.append("\n");
    _stringBuilder.append("    ");
    final String _sql = _stringBuilder.toString();
    final int _argCount = 0 + _inputSize;
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, _argCount);
    int _argIndex = 1;
    for (Long _item : rootCommentIds) {
      if (_item == null) {
        _statement.bindNull(_argIndex);
      } else {
        _statement.bindLong(_argIndex, _item);
      }
      _argIndex++;
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<CommentEntity>>() {
      @Override
      @NonNull
      public List<CommentEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfBy = CursorUtil.getColumnIndexOrThrow(_cursor, "by");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfKids = CursorUtil.getColumnIndexOrThrow(_cursor, "kids");
          final int _cursorIndexOfParent = CursorUtil.getColumnIndexOrThrow(_cursor, "parent");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final List<CommentEntity> _result = new ArrayList<CommentEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final CommentEntity _item_1;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpBy;
            if (_cursor.isNull(_cursorIndexOfBy)) {
              _tmpBy = null;
            } else {
              _tmpBy = _cursor.getString(_cursorIndexOfBy);
            }
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final List<Long> _tmpKids;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfKids)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfKids);
            }
            _tmpKids = __converters.toLongList(_tmp);
            final Long _tmpParent;
            if (_cursor.isNull(_cursorIndexOfParent)) {
              _tmpParent = null;
            } else {
              _tmpParent = _cursor.getLong(_cursorIndexOfParent);
            }
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            _item_1 = new CommentEntity(_tmpId,_tmpBy,_tmpTime,_tmpText,_tmpKids,_tmpParent,_tmpType,_tmpFetchedAt);
            _result.add(_item_1);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object getCommentCount(final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM comments";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteComments(final List<Long> ids, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("DELETE FROM comments WHERE id IN (");
        final int _inputSize = ids.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        for (Long _item : ids) {
          if (_item == null) {
            _stmt.bindNull(_argIndex);
          } else {
            _stmt.bindLong(_argIndex, _item);
          }
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

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
import com.hackernews.futuristic.data.local.entities.Converters;
import com.hackernews.futuristic.data.local.entities.StoryEntity;
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
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class StoryDao_Impl implements StoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<StoryEntity> __insertionAdapterOfStoryEntity;

  private final Converters __converters = new Converters();

  private final SharedSQLiteStatement __preparedStmtOfDeleteOldStories;

  private final SharedSQLiteStatement __preparedStmtOfDeleteStoriesByType;

  private final SharedSQLiteStatement __preparedStmtOfCleanupOldStories;

  public StoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfStoryEntity = new EntityInsertionAdapter<StoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `stories` (`id`,`title`,`url`,`text`,`by`,`time`,`score`,`descendants`,`kids`,`type`,`storyType`,`fetchedAt`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final StoryEntity entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getUrl() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getUrl());
        }
        if (entity.getText() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getText());
        }
        if (entity.getBy() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getBy());
        }
        statement.bindLong(6, entity.getTime());
        statement.bindLong(7, entity.getScore());
        if (entity.getDescendants() == null) {
          statement.bindNull(8);
        } else {
          statement.bindLong(8, entity.getDescendants());
        }
        final String _tmp = __converters.fromLongList(entity.getKids());
        if (_tmp == null) {
          statement.bindNull(9);
        } else {
          statement.bindString(9, _tmp);
        }
        if (entity.getType() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getType());
        }
        if (entity.getStoryType() == null) {
          statement.bindNull(11);
        } else {
          statement.bindString(11, entity.getStoryType());
        }
        statement.bindLong(12, entity.getFetchedAt());
      }
    };
    this.__preparedStmtOfDeleteOldStories = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM stories WHERE storyType = ? AND fetchedAt < ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteStoriesByType = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM stories WHERE storyType = ?";
        return _query;
      }
    };
    this.__preparedStmtOfCleanupOldStories = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM stories WHERE fetchedAt < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insertStories(final List<StoryEntity> stories,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStoryEntity.insert(stories);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object insertStory(final StoryEntity story, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfStoryEntity.insert(story);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteOldStories(final String type, final long olderThan,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteOldStories.acquire();
        int _argIndex = 1;
        if (type == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, type);
        }
        _argIndex = 2;
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
          __preparedStmtOfDeleteOldStories.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteStoriesByType(final String type,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteStoriesByType.acquire();
        int _argIndex = 1;
        if (type == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, type);
        }
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
          __preparedStmtOfDeleteStoriesByType.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object cleanupOldStories(final long cutoffTime,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfCleanupOldStories.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, cutoffTime);
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
          __preparedStmtOfCleanupOldStories.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<StoryEntity>> getStoriesByType(final String type) {
    final String _sql = "SELECT * FROM stories WHERE storyType = ? ORDER BY time DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, type);
    }
    return CoroutinesRoom.createFlow(__db, false, new String[] {"stories"}, new Callable<List<StoryEntity>>() {
      @Override
      @NonNull
      public List<StoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfBy = CursorUtil.getColumnIndexOrThrow(_cursor, "by");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfDescendants = CursorUtil.getColumnIndexOrThrow(_cursor, "descendants");
          final int _cursorIndexOfKids = CursorUtil.getColumnIndexOrThrow(_cursor, "kids");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStoryType = CursorUtil.getColumnIndexOrThrow(_cursor, "storyType");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final List<StoryEntity> _result = new ArrayList<StoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StoryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpBy;
            if (_cursor.isNull(_cursorIndexOfBy)) {
              _tmpBy = null;
            } else {
              _tmpBy = _cursor.getString(_cursorIndexOfBy);
            }
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            final Integer _tmpDescendants;
            if (_cursor.isNull(_cursorIndexOfDescendants)) {
              _tmpDescendants = null;
            } else {
              _tmpDescendants = _cursor.getInt(_cursorIndexOfDescendants);
            }
            final List<Long> _tmpKids;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfKids)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfKids);
            }
            _tmpKids = __converters.toLongList(_tmp);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpStoryType;
            if (_cursor.isNull(_cursorIndexOfStoryType)) {
              _tmpStoryType = null;
            } else {
              _tmpStoryType = _cursor.getString(_cursorIndexOfStoryType);
            }
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            _item = new StoryEntity(_tmpId,_tmpTitle,_tmpUrl,_tmpText,_tmpBy,_tmpTime,_tmpScore,_tmpDescendants,_tmpKids,_tmpType,_tmpStoryType,_tmpFetchedAt);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getStoriesByTypeSync(final String type, final int limit,
      final Continuation<? super List<StoryEntity>> $completion) {
    final String _sql = "SELECT * FROM stories WHERE storyType = ? ORDER BY time DESC LIMIT ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, type);
    }
    _argIndex = 2;
    _statement.bindLong(_argIndex, limit);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<StoryEntity>>() {
      @Override
      @NonNull
      public List<StoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfBy = CursorUtil.getColumnIndexOrThrow(_cursor, "by");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfDescendants = CursorUtil.getColumnIndexOrThrow(_cursor, "descendants");
          final int _cursorIndexOfKids = CursorUtil.getColumnIndexOrThrow(_cursor, "kids");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStoryType = CursorUtil.getColumnIndexOrThrow(_cursor, "storyType");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final List<StoryEntity> _result = new ArrayList<StoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StoryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpBy;
            if (_cursor.isNull(_cursorIndexOfBy)) {
              _tmpBy = null;
            } else {
              _tmpBy = _cursor.getString(_cursorIndexOfBy);
            }
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            final Integer _tmpDescendants;
            if (_cursor.isNull(_cursorIndexOfDescendants)) {
              _tmpDescendants = null;
            } else {
              _tmpDescendants = _cursor.getInt(_cursorIndexOfDescendants);
            }
            final List<Long> _tmpKids;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfKids)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfKids);
            }
            _tmpKids = __converters.toLongList(_tmp);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpStoryType;
            if (_cursor.isNull(_cursorIndexOfStoryType)) {
              _tmpStoryType = null;
            } else {
              _tmpStoryType = _cursor.getString(_cursorIndexOfStoryType);
            }
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            _item = new StoryEntity(_tmpId,_tmpTitle,_tmpUrl,_tmpText,_tmpBy,_tmpTime,_tmpScore,_tmpDescendants,_tmpKids,_tmpType,_tmpStoryType,_tmpFetchedAt);
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
  public Object getStoryById(final long id, final Continuation<? super StoryEntity> $completion) {
    final String _sql = "SELECT * FROM stories WHERE id = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<StoryEntity>() {
      @Override
      @Nullable
      public StoryEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfBy = CursorUtil.getColumnIndexOrThrow(_cursor, "by");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfDescendants = CursorUtil.getColumnIndexOrThrow(_cursor, "descendants");
          final int _cursorIndexOfKids = CursorUtil.getColumnIndexOrThrow(_cursor, "kids");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStoryType = CursorUtil.getColumnIndexOrThrow(_cursor, "storyType");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final StoryEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpBy;
            if (_cursor.isNull(_cursorIndexOfBy)) {
              _tmpBy = null;
            } else {
              _tmpBy = _cursor.getString(_cursorIndexOfBy);
            }
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            final Integer _tmpDescendants;
            if (_cursor.isNull(_cursorIndexOfDescendants)) {
              _tmpDescendants = null;
            } else {
              _tmpDescendants = _cursor.getInt(_cursorIndexOfDescendants);
            }
            final List<Long> _tmpKids;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfKids)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfKids);
            }
            _tmpKids = __converters.toLongList(_tmp);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpStoryType;
            if (_cursor.isNull(_cursorIndexOfStoryType)) {
              _tmpStoryType = null;
            } else {
              _tmpStoryType = _cursor.getString(_cursorIndexOfStoryType);
            }
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            _result = new StoryEntity(_tmpId,_tmpTitle,_tmpUrl,_tmpText,_tmpBy,_tmpTime,_tmpScore,_tmpDescendants,_tmpKids,_tmpType,_tmpStoryType,_tmpFetchedAt);
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
  public Object getStoriesByIds(final List<Long> ids,
      final Continuation<? super List<StoryEntity>> $completion) {
    final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
    _stringBuilder.append("SELECT * FROM stories WHERE id IN (");
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
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<StoryEntity>>() {
      @Override
      @NonNull
      public List<StoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "url");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfBy = CursorUtil.getColumnIndexOrThrow(_cursor, "by");
          final int _cursorIndexOfTime = CursorUtil.getColumnIndexOrThrow(_cursor, "time");
          final int _cursorIndexOfScore = CursorUtil.getColumnIndexOrThrow(_cursor, "score");
          final int _cursorIndexOfDescendants = CursorUtil.getColumnIndexOrThrow(_cursor, "descendants");
          final int _cursorIndexOfKids = CursorUtil.getColumnIndexOrThrow(_cursor, "kids");
          final int _cursorIndexOfType = CursorUtil.getColumnIndexOrThrow(_cursor, "type");
          final int _cursorIndexOfStoryType = CursorUtil.getColumnIndexOrThrow(_cursor, "storyType");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final List<StoryEntity> _result = new ArrayList<StoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final StoryEntity _item_1;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpUrl;
            if (_cursor.isNull(_cursorIndexOfUrl)) {
              _tmpUrl = null;
            } else {
              _tmpUrl = _cursor.getString(_cursorIndexOfUrl);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpBy;
            if (_cursor.isNull(_cursorIndexOfBy)) {
              _tmpBy = null;
            } else {
              _tmpBy = _cursor.getString(_cursorIndexOfBy);
            }
            final long _tmpTime;
            _tmpTime = _cursor.getLong(_cursorIndexOfTime);
            final int _tmpScore;
            _tmpScore = _cursor.getInt(_cursorIndexOfScore);
            final Integer _tmpDescendants;
            if (_cursor.isNull(_cursorIndexOfDescendants)) {
              _tmpDescendants = null;
            } else {
              _tmpDescendants = _cursor.getInt(_cursorIndexOfDescendants);
            }
            final List<Long> _tmpKids;
            final String _tmp;
            if (_cursor.isNull(_cursorIndexOfKids)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getString(_cursorIndexOfKids);
            }
            _tmpKids = __converters.toLongList(_tmp);
            final String _tmpType;
            if (_cursor.isNull(_cursorIndexOfType)) {
              _tmpType = null;
            } else {
              _tmpType = _cursor.getString(_cursorIndexOfType);
            }
            final String _tmpStoryType;
            if (_cursor.isNull(_cursorIndexOfStoryType)) {
              _tmpStoryType = null;
            } else {
              _tmpStoryType = _cursor.getString(_cursorIndexOfStoryType);
            }
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            _item_1 = new StoryEntity(_tmpId,_tmpTitle,_tmpUrl,_tmpText,_tmpBy,_tmpTime,_tmpScore,_tmpDescendants,_tmpKids,_tmpType,_tmpStoryType,_tmpFetchedAt);
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
  public Object getStoryCount(final String type, final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM stories WHERE storyType = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, type);
    }
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
  public Object getLastFetchTime(final String type, final Continuation<? super Long> $completion) {
    final String _sql = "SELECT fetchedAt FROM stories WHERE storyType = ? ORDER BY fetchedAt DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (type == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, type);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Long>() {
      @Override
      @Nullable
      public Long call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Long _result;
          if (_cursor.moveToFirst()) {
            if (_cursor.isNull(0)) {
              _result = null;
            } else {
              _result = _cursor.getLong(0);
            }
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}

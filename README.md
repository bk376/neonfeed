# NeonFeed

A fast, cyberpunk-styled Hacker News reader for Android (Kotlin, Jetpack Compose).

## Features

- **Home** – live HN front page with infinite scroll, pull-to-refresh and a time filter.
- **Search** – full-text search over all of HN (stories or comments), ranked by relevance or newest, with a time-range filter.
- **Comments** – the whole thread loads in one request; tap a comment header to collapse it, a "next thread" button jumps between top-level threads, OP is marked, and links are tappable.
- **User pages** – karma, account age, bio, and paged submissions and comments.
- **Bookmarks** – stored locally in Room.
- **Themes** – Neon, AMOLED, Dark, Light, or follow the system (palette icon on Home).
- **In-app browser** – with share and open-in-browser actions.

## Data sources

| What | API |
| --- | --- |
| Front page ranking, stories, users | [HN Firebase API](https://github.com/HackerNews/API) |
| Search, comment trees, user submissions/comments | [HN Search API (Algolia)](https://hn.algolia.com/api) |

Neither requires an API key.

## Build

```bash
./gradlew assembleDebug      # app/build/outputs/apk/debug/app-debug.apk
./gradlew installDebug       # install on a connected device
```

Requires JDK 17 and the Android SDK (`sdk.dir` in `local.properties`).

## Structure

```
app/src/main/java/com/hackernews/futuristic/
├── data/
│   ├── api/          HackerNewsApi (Firebase), AlgoliaApi
│   ├── local/        Room: story cache (5 min TTL), bookmarks
│   ├── model/        Story, CommentNode, CommentHit
│   └── repository/   HackerNewsRepository, SettingsRepository (theme)
├── di/               Hilt modules
└── ui/
    ├── stories/      Home feed
    ├── search/
    ├── detail/       Story + comment thread
    ├── user/
    ├── bookmarks/
    ├── browser/
    ├── settings/     Theme picker
    ├── components/   StoryCard, CommentHitCard, HtmlText, shared bits
    └── theme/        AppPalette / AppTheme definitions
```

# NeonFeed

Personal Hacker News reader for Android (Kotlin, Jetpack Compose, Hilt, Room, Retrofit). See README.md for features and layout.

## Commands
- Build: `./gradlew assembleDebug` → `app/build/outputs/apk/debug/app-debug.apk`
- Install: `./gradlew installDebug`
- Release APKs are kept in `releases/` (the only tracked APKs).

## Conventions
- Colors: always use `AppColors.<role>` (from `ui/theme/Color.kt`), never hardcoded `Color(0x…)`, so every theme works. Add a new role to `AppPalette` if needed and set it in every palette.
- Firebase API (`HackerNewsApi`) for live ranking/scores/users; Algolia (`AlgoliaApi`) for search, comment trees and user activity.
- Comment trees come from Algolia `items/{id}`; top-level order is re-ranked using Firebase `kids`.
- Story cache: Room, 5-minute freshness, pruned after 24h. Bookmarks live in the same DB — any schema change needs a real `Migration`, never a destructive fallback.
- Shared UI lives in `ui/components` (`StoryCard`, `CommentHitCard`, `HtmlText`, `LoadMoreEffect`, `CenteredLoading/Message`).

## Scope
The owner uses the feed, search, comments, user pages and bookmarks. The earlier experimental features (neural analysis, quantum search, biometrics, AR, holographic mode, AI daily summary) were removed on purpose — don't reintroduce them. Next idea on the list: grouping the feed by topic.

# KotlinLab2 — счётчик + переключение текста

Небольшое Android‑приложение на **Kotlin + Compose**: по центру экрана показывается текст, внизу — красная кнопка **«Изменить»**. 
При нажатии:
- сообщение переключается циклично между несколькими вариантами,
- увеличивается счётчик нажатий и отображается в тексте.

## Фичи
- Jetpack Compose, без XML
- Центрированный текст
- Кнопка всегда прижата к нижнему центру (учитывает системную навигацию)
- Счётчик вынесен в отдельный класс `Counter` с `Saver` для `rememberSaveable`

## Технологии
- Kotlin 1.9+
- Android Gradle Plugin (AGP) 8.x
- Jetpack Compose (Material 3)
- Минимальная SDK: 21+
- Рекомендованный JDK: 17

## Требования к окружению (Linux/Ubuntu)
- Android Studio (последняя стабильная)
- SDK-компоненты: Android Emulator, Platform-Tools, Build-Tools, system image x86_64
- Для эмулятора: включён KVM (`kvm-ok` → KVM acceleration can be used)

## Быстрый старт в Android Studio
1. Откройте проект в Android Studio.
2. Создайте AVD в Device Manager (x86_64 system image).
3. Нажмите **Run ‘app’** — приложение соберётся и установится на эмулятор.

## CLI (опционально)

```bash
./gradlew assembleDebug
adb install -r app/build/outputs/apk/debug/app-debug.apk
adb shell am start -n com.example.counter/.MainActivity
```

## Ключевые файлы
- `app/src/main/java/com/example/counter/MainActivity.kt` — UI и логика (Compose)
- `app/src/main/AndroidManifest.xml` — манифест (входная Activity)
- Файлы сборки: `build.gradle`, `settings.gradle`, `gradle.properties`
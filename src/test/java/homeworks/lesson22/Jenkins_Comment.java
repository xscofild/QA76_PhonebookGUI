package homeworks.lesson22;

/*
 * ============================================================
 *  JENKINS — Урок 22 | 06.03.2026
 *  PhonebookGUI — CI/CD подключение и работа
 * ============================================================
 *
 * ============================================================
 *  ЭТАП 1 — СКАЧАТЬ И ЗАПУСТИТЬ JENKINS
 * ============================================================
 *
 *  1. Скачать: https://www.jenkins.io/download
 *     Выбрать: универсальный Java пакет  .war
 *
 *  2. Положить jenkins.war в папку Tools
 *
 *  3. Открыть CMD в папке Tools и запустить:
 *       java -jar jenkins.war
 *
 *  4. Дождаться пока в консоли появится пароль (скопировать!)
 *
 *  5. Открыть браузер: http://localhost:8080
 *     (Jenkins может работать и на других портах: 8383, 9090 и т.д.)
 *
 *  6. Вставить скопированный Administrator password  Continue
 *
 *  7. Нажать: Install suggested plugins
 *     (Jenkins сам устанавливает нужные плагины — ждём)
 *
 *  8. Создать пользователя:
 *       - Имя пользователя
 *       - Пароль
 *       - Повтор пароля
 *       - Имя Фамилия
 *       - Email адрес
 *     Save and Continue  Save and Finish  Start using Jenkins
 *
 *  Выключить Jenkins: в CMD нажать Ctrl + C
 *
 * ============================================================
 *  ЭТАП 2 — НАСТРОЙКА TOOLS
 *  Manage Jenkins  Tools
 * ============================================================
 *
 *  JDK:
 *    Узнать путь через CMD:
 *      where java
 *      echo %JAVA_HOME%
 *    Name: jdk-17
 *    JAVA_HOME: C:\Program Files\Java\jdk-17
 *    Install automatically: НЕ ставить галочку
 *
 *  Git:
 *    Name: Default
 *    Path to Git executable: C:\Program Files\Git\bin\git.exe
 *    Install automatically: НЕ ставить галочку
 *
 *  Gradle:
 *    Name: gradle-9.1.0
 *    GRADLE_HOME: C:\Tools\gradle-9.1.0
 *    Install automatically: НЕ ставить галочку
 *
 * ============================================================
 *  ЭТАП 3 — СОЗДАНИЕ JOB (первый проект)
 *  New Item  Enter item name  Freestyle project  OK
 * ============================================================
 *
 *  Название: QA76m_PhonebookGUI_Firefox_Homework22
 *
 *  [Source Code Management]
 *    Выбрать: Git
 *    Repository URL: https://github.com/xscofild/QA76_PhonebookGUI
 *    Нажать Apply (проверить что нет ошибок подключения)
 *    Branch Specifier: *  *  (любая ветка)
 *
 *  [Build Steps]
 *    Add build step  Invoke Gradle script
 *    Gradle Version: gradle-9.1.0
 *    Tasks: clean HW22Jenkins
 *    (task берём из build.gradle нашего проекта в IDEA)
 *
 *  Нажать Save  затем Build Now для запуска
 *  Если ошибка  смотреть: Console Output
 *
 * ============================================================
 *  ЭТАП 4 — СОЗДАНИЕ JOB (копирование из существующего)
 *  New Item  Enter item name
 * ============================================================
 *
 *  Внизу страницы: Copy from
 *    Ввести имя предыдущего Job  он подтянет все настройки
 *
 *  Менять только Tasks, например:
 *    clean regression
 *
 *  Запуск на другом браузере через параметр:
 *    -Pbrowser=firefox clean regression
 *
 * ============================================================
 *  ЭТАП 5 — POST BUILD ACTIONS (логи и отчёты)
 * ============================================================
 *
 *  Post-build Actions  Add post-build action
 *    Publish JUnit test result report
 *    Test report XMLs:
 *      build/test-results/*  *.xml
 *
 * ============================================================
 *  ЭТАП 6 — TRIGGERS (автозапуск по расписанию)
 *  Build Triggers  Build periodically
 * ============================================================
 *
 *  Формат: минуты часы день месяц деньНедели
 *
 *  Популярные примеры:
 *    H 8 * * 1-5   — каждый будний день в 8:00
 *    H 8 * * *     — каждый день в 8:00
 *    H * * * *     — каждый час
 *    H/15 * * * *  — каждые 15 минут
 *    H 8,20 * * *  — в 8:00 и в 20:00 каждый день
 *    H 8 * * 1     — каждый понедельник в 8:00
 *
 * ============================================================
 */

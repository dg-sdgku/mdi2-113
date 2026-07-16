# MDI2-113 - Advanced Wearables

Repository for the project used during **MDI2-113 - Advanced Wearables**.

This course continues the work with a Wear OS StepCounter application and its Android companion app, including the configuration needed to run and evolve both parts of the wearable experience.

## Project Structure

This repository contains two Android applications:

- `wear`: Wear OS StepCounter application.
- `mobile`: Android companion application used to communicate with the Wear OS app.

## Running the Project

1. Open the repository root in Android Studio.
2. Allow Gradle to synchronize.
3. Run the Wear OS module on a Wear OS emulator.
4. Run the mobile module on an Android phone emulator.
5. Pair the phone and Wear OS emulators using Android Studio's Wear OS Pairing Assistant.

## Branch Strategy

- `main`: latest and most complete version of the project.
- `class-01`: project state at the end of session 1.
- `class-02`: project state at the end of session 2.
- `class-03`: project state at the end of session 3.
- `class-04`: project state at the end of session 4.

Students can switch to the branch that corresponds to each class to review the accumulated code up to that session.

## Useful Commands

```bash
git clone https://github.com/dg-sdgku/mdi2-113.git
cd mdi2-113
git fetch --all
git switch class-01
```

To update the latest complete version:

```bash
git switch main
git pull origin main
```

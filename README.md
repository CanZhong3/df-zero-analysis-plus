# HW1 plus: Zero Analysis with an extended lattice

In this homework you make the zero analysis of HW1 more precise: the lattice gets three new values
(≤0, ≥0 and ≠0), so fewer divisions are reported as possible divisions by zero.

**Read the whole assignment before starting:**
[`assignment/DF-HW1-plus-Assignment.pdf`](assignment/DF-HW1-plus-Assignment.pdf) (it is also on Moodle, with the deadlines).

## 1. Get your own copy of this repo

1. Sign in to GitHub and click **Fork** (top right of this page). Pick your account as the owner and
   keep the repo name. Working in a group? Only one member forks, and adds the others in the fork under
   **Settings → Collaborators**.
2. In your fork, open the **Actions** tab and click **"I understand my workflows, go ahead and enable
   them"**. From then on GitHub runs the tests on every push and shows the result next to each commit.
3. Clone **your fork**, not this repo:

   ```bash
   git clone https://github.com/<your-user>/df-zero-analysis-plus.git
   ```

Forks of this repo are public. Write your own solution: copying from someone else's fork is plagiarism.

## 2. Install (once)

The same as in HW1: a **JDK 17 or newer** (check it with `java -version`) and an editor, for example
VS Code with the **Extension Pack for Java** (`vscjava.vscode-java-pack`), or IntelliJ IDEA. Gradle
and Tai-e are downloaded automatically the first time you build the project.

### If your setup doesn't work: Docker

If you can't get the JDK working, or the downloads never finish, use the course's Docker image: it has
everything already installed and works without internet. Follow
[these instructions](https://github.com/gtiit-cs-seminar/df-env/blob/main/STUDENTS.md). You don't need it
otherwise: the steps above are all the homework needs.

## 3. The homework

**Start from your HW1 code.** Copy these three files from your HW1 repo into the same folder of this
one, `src/main/java/seminar/dataflow/zeroanalysis/`, replacing the ones here:

- `ZeroValueVisitor.java`
- `ZeroAbstractState.java`
- `DivisionByZeroAnalysis.java`

Don't copy `ZeroAbstractValue.java`: here it has the eight values of the extended lattice (`BOTTOM`,
`NEGATIVE`, `ZERO`, `POSITIVE`, `NON_POSITIVE`, `NON_NEGATIVE`, `NOT_ZERO`, `TOP`).

**Part 1** (on paper, not submitted): fill the tables of the assignment for the eight values.

**Part 2**:

- implement `add`, `substract`, `multiplyBy`, `divideBy` and `merge` of `ZeroAbstractValue` for the
  eight values, as precisely as possible;
- revise `maybeZeroOperand` in `ZeroValueVisitor`, and anything else in your HW1 code that assumes five
  values.

Don't change the other files (the framework, the `targets` and the tests): we grade your code with these
tests plus hidden ones. You can add tests of your own.

## 4. Run the tests and the analysis

In a terminal, from the project folder (on Windows, use `gradlew.bat` instead of `./gradlew`):

```bash
./gradlew test                                                                  # all tests
./gradlew test --tests 'dataflow.zeroanalysis.*'                                # some of them
./gradlew zeroAnalysis -PtargetClass=seminar.dataflow.targets.ZeroAnalysis8       # analysis on one class
```

`zeroAnalysis` prints Tai-e's IR of each method together with the state your analysis computed for each
statement: when a test fails, run it on the test's target and compare.

In VS Code you can also use **Terminal → Run Task...**: "Tests (gradlew test)" and "Division-by-zero
analysis on a class". The test report ends up in `build/reports/tests/test/index.html`.

## 5. Submit

1. Commit and push to the `main` branch of your fork, as often as you like:

   ```bash
   git add -A
   git commit -m "Extend the transfer functions to eight values"
   git push
   ```

2. Check in the **Actions** tab of your fork that the last run passes.
3. Submit the URL of your fork on Moodle.

We grade the last commit on `main` pushed before the deadline, with the tests in this repo plus hidden
tests that check every operation of `ZeroAbstractValue` on every pair of values against the most precise
result.

## 6. Updates from the course

If we fix something in this repo, your fork's page says "This branch is N commits behind". Click
**Sync fork → Update branch**, and then run `git pull` in your clone.

## Common problems

- **"Gradle requires JVM 17 or later to run"**: the JDK in use is older than 17. Check `java -version`
  (and `JAVA_HOME`, if set); in VS Code, check the JDK the Java extension uses ("Java: Configure Java Runtime").
- **Downloads are very slow or time out** (e.g. in mainland China): uncomment the mirror line in the
  `repositories` block of `build.gradle`. If the Gradle download itself is the slow part, change
  `distributionUrl` in `gradle/wrapper/gradle-wrapper.properties` to
  `https\://mirrors.cloud.tencent.com/gradle/gradle-9.8.1-bin.zip`.
- **`./gradlew: Permission denied`** (macOS/Linux): run `sh ./gradlew test`, or `chmod +x gradlew` once.
- **No test results in the Actions tab**: the workflows of a fork start disabled; enable them (step 1.2).

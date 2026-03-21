# Session Summary

## What Was Done

### 1. Created a reusable git diff review prompt
- **`prompts/diff-review.md`** — A single prompt template for reviewing any git diff. Paste the diff at the bottom and send to an LLM. Produces a 6-section structured review:
  1. Summary (high-level description, files changed, risk level)
  2. What Changed (per-file plain-language description)
  3. Code Quality Assessment (correctness, design, idioms, readability, completeness)
  4. Potential Issues (file:line + severity)
  5. Suggestions for Improvement (actionable, location-specific)
  6. Positive Observations

  Started as two separate prompts (review + summary), then merged into one at user request.

### 2. Tested the prompt on HEAD~1 → HEAD of this repo
- Ran `git diff HEAD~1 HEAD` — the diff covers 4 new algorithm exercise Java files added in the latest commit.
- Applied the prompt to produce a full review.

### 3. Output files written to `output/diff_HEAD_1_HEAD/`
- **`diff.txt`** — raw git diff output
- **`review.md`** — full review produced by applying the prompt

---

## Key Findings from the Review

| File | Issues |
| --- | --- |
| `ThreadPrint.java` | 3 High issues: 6 threads created for a 3-thread design; `future.isDone()` is a no-op (executor never shut down); potential deadlock from fragile signal/await ordering |
| `Solution_12347894.java` | 1 High: `reverseList` returns a list with a spurious trailing null node (dummy-head bug); 1 Medium: type parameter named `Integer` shadows `java.lang.Integer` |
| `Main_478307.java` | 1 Low: prints `"null "` before the list instead of after |
| `ConcurrentCache.java` | 1 Low: 3 unused imports; class is an empty skeleton that doesn't implement its own `Cache` interface |

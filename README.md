# SMT-Reducer

Very simple delta debugging tool for SMT formulae based on hierarchical delta debugging algorithm. Currently only supports Z3 solver.

## Usage
```
./gradlew run --args="formulae timeout mode [optional mode arguments]"
```

Options:
* `formula` &mdash; path to a file containing an SMT formula in SMTLIB2 format.
* `timeout` &mdash; global timeout for delta debugging, in seconds.
* `mode` &mdash; mode in which to run the delta debugging; basicly means what condition reproducing checker is used. Currently supports two modes:
  * `proof` &mdash; reduces the unsatisfiable smt formula while the top-level proof condition remains the same; no additional arguments required.
  * `timeout` &mdash; reduces the smt formula until the solver ends with a given timeout; additional arguments:
     * solver timeout, in seconds.

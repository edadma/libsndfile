libsndfile
==========

![GitHub release (latest by date including pre-releases)](https://img.shields.io/github/v/release/edadma/libsndfile?include_prereleases) ![GitHub (Pre-)Release Date](https://img.shields.io/github/release-date-pre/edadma/libsndfile) ![GitHub last commit](https://img.shields.io/github/last-commit/edadma/libsndfile) ![GitHub](https://img.shields.io/github/license/edadma/libsndfile)

*libsndfile* provides Scala Native bindings for the [Libsndfile](https://tiswww.cwru.edu/php/chet/libsndfile/rltop.html) C library for sampled sound manipulation.

Overview
--------

The goal of this project is to provide an easy-to-use Scala Native facade for the entire *libsndfile* C library for sampled sound manipulation.  Currently, all the functions and constants needed to write integer sampled sound to all the formats supported by the library are supported.  Development is active, and support for the remaining functionality of the library will be added in upcoming releases.

The more "programmer friendly" part of this library is found in the `io.github.edadma.libsndfile.facade` package.  That's the only package you need to import from, as seen in the example below.  The other package in the library is `io.github.edadma.libsndfile.extern` which provides for interaction with the libsndfile C library using Scala Native interoperability elements from the so-call `unsafe` namespace.  There are no public declarations in the `io.github.edadma.libsndfile.facade` package that use `unsafe` types in their parameter or return types, making it a pure Scala facade.  Consequently, you never have to worry about memory allocation or type conversions.

Usage
-----

To use this library, `libsndfile1-dev` needs to be installed:

```shell
sudo apt install libsndfile1-dev
```

Include the following in your `build.sbt`:

```scala
resolvers += Resolver.githubPackages("edadma")

libraryDependencies += "io.github.edadma" %%% "libsndfile" % "0.1.0"
```

Use the following `import` in your code:

```scala
import io.github.edadma.libsndfile.facade._
```

Example
-------


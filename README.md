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

Include the following in your `project/plugins.sbt`:

```sbt
addSbtPlugin("com.codecommit" % "sbt-github-packages" % "0.5.2")
```

Include the following in your `build.sbt`:

```sbt
resolvers += Resolver.githubPackages("edadma")

libraryDependencies += "io.github.edadma" %%% "libsndfile" % "0.1.2"
```

Use the following `import` in your code:

```scala
import io.github.edadma.libsndfile.facade._
```

Example
-------

This example is a direct translation of the [make_sine](https://github.com/libsndfile/libsndfile/blob/master/examples/make_sine.c) example in the C library repository.  It generates a [WAV](https://en.wikipedia.org/wiki/WAV) uncompressed audio file called "sine.wav", which plays a 4-second sound at 344Hz in the left channel and 466Hz in the right channel.

```scala
import math._

import io.github.edadma.libsndfile.facade._

object Main extends App {

  val SAMPLE_RATE  = 44100
  val SAMPLE_COUNT = SAMPLE_RATE * 4 /* 4 seconds */
  val AMPLITUDE    = 1.0 * 0x7F000000
  val LEFT_FREQ    = 344.0 / SAMPLE_RATE
  val RIGHT_FREQ   = 466.0 / SAMPLE_RATE

  val (sndfile, sfinfo) = sf_open("sine.wav",
                                  SFM_WRITE,
                                  SFInfo(samplerate = SAMPLE_RATE,
                                         frames = SAMPLE_COUNT,
                                         channels = 2,
                                         format = SF_FORMAT_WAV subtype SF_FORMAT_PCM_24))

  if (sfinfo.channels > 2) {
    println("Error : can only generate mono or stereo files.")
    sndfile.close
    sys.exit(1)
  }

  def sample(k: Int): Int =
    if (sfinfo.channels == 1) (AMPLITUDE * sin(LEFT_FREQ * 2 * k * Pi)).toInt
    else { // stereo
      if ((k & 1) == 0) // left channel indexes are even
        (AMPLITUDE * sin(LEFT_FREQ * k * Pi)).toInt // k = 2*i where i is the right channel sample
      else (AMPLITUDE * sin(RIGHT_FREQ * (k - 1) * Pi)).toInt // k = 2*i + 1 where i is the left channel sample
    }

  if (sndfile.write_int(sample, sfinfo.channels * SAMPLE_COUNT) != sfinfo.channels * SAMPLE_COUNT)
    println(sndfile.strerror)

  sndfile.close

}
```

Documentation
-------------

API documentation is forthcoming, however documentation for the C library is found [here](http://libsndfile.github.io/libsndfile/api.html).

License
-------

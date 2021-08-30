package io.github.edadma.libsndfile.extern

import io.github.edadma.libsndfile.facade.FormatType

import scala.scalanative.unsafe._

@link("sndfile")
@extern
object LibSndfile {
  type sf_count_t = CLong

  type SNDFILE = Ptr[Byte]

  type SF_INFO = CStruct6[sf_count_t, CInt, CInt, CInt, CInt, CInt]

  def sf_open(path: CString, mode: CInt, sfinfo: Ptr[SF_INFO]): Ptr[SNDFILE] = extern

}

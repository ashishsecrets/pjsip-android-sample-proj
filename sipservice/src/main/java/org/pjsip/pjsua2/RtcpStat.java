/* ----------------------------------------------------------------------------
 * This file was automatically generated by SWIG (http://www.swig.org).
 * Version 4.0.2
 *
 * Do not make changes to this file unless you know what you are doing--modify
 * the SWIG interface file instead.
 * ----------------------------------------------------------------------------- */

package org.pjsip.pjsua2;

public class RtcpStat {
  private transient long swigCPtr;
  protected transient boolean swigCMemOwn;

  protected RtcpStat(long cPtr, boolean cMemoryOwn) {
    swigCMemOwn = cMemoryOwn;
    swigCPtr = cPtr;
  }

  protected static long getCPtr(RtcpStat obj) {
    return (obj == null) ? 0 : obj.swigCPtr;
  }

  @SuppressWarnings("deprecation")
  protected void finalize() {
    delete();
  }

  public synchronized void delete() {
    if (swigCPtr != 0) {
      if (swigCMemOwn) {
        swigCMemOwn = false;
        pjsua2JNI.delete_RtcpStat(swigCPtr);
      }
      swigCPtr = 0;
    }
  }

  public void setStart(TimeVal value) {
    pjsua2JNI.RtcpStat_start_set(swigCPtr, this, TimeVal.getCPtr(value), value);
  }

  public TimeVal getStart() {
    long cPtr = pjsua2JNI.RtcpStat_start_get(swigCPtr, this);
    return (cPtr == 0) ? null : new TimeVal(cPtr, false);
  }

  public void setTxStat(RtcpStreamStat value) {
    pjsua2JNI.RtcpStat_txStat_set(swigCPtr, this, RtcpStreamStat.getCPtr(value), value);
  }

  public RtcpStreamStat getTxStat() {
    long cPtr = pjsua2JNI.RtcpStat_txStat_get(swigCPtr, this);
    return (cPtr == 0) ? null : new RtcpStreamStat(cPtr, false);
  }

  public void setRxStat(RtcpStreamStat value) {
    pjsua2JNI.RtcpStat_rxStat_set(swigCPtr, this, RtcpStreamStat.getCPtr(value), value);
  }

  public RtcpStreamStat getRxStat() {
    long cPtr = pjsua2JNI.RtcpStat_rxStat_get(swigCPtr, this);
    return (cPtr == 0) ? null : new RtcpStreamStat(cPtr, false);
  }

  public void setRttUsec(MathStat value) {
    pjsua2JNI.RtcpStat_rttUsec_set(swigCPtr, this, MathStat.getCPtr(value), value);
  }

  public MathStat getRttUsec() {
    long cPtr = pjsua2JNI.RtcpStat_rttUsec_get(swigCPtr, this);
    return (cPtr == 0) ? null : new MathStat(cPtr, false);
  }

  public void setRtpTxLastTs(long value) {
    pjsua2JNI.RtcpStat_rtpTxLastTs_set(swigCPtr, this, value);
  }

  public long getRtpTxLastTs() {
    return pjsua2JNI.RtcpStat_rtpTxLastTs_get(swigCPtr, this);
  }

  public void setRtpTxLastSeq(int value) {
    pjsua2JNI.RtcpStat_rtpTxLastSeq_set(swigCPtr, this, value);
  }

  public int getRtpTxLastSeq() {
    return pjsua2JNI.RtcpStat_rtpTxLastSeq_get(swigCPtr, this);
  }

  public void setRxIpdvUsec(MathStat value) {
    pjsua2JNI.RtcpStat_rxIpdvUsec_set(swigCPtr, this, MathStat.getCPtr(value), value);
  }

  public MathStat getRxIpdvUsec() {
    long cPtr = pjsua2JNI.RtcpStat_rxIpdvUsec_get(swigCPtr, this);
    return (cPtr == 0) ? null : new MathStat(cPtr, false);
  }

  public void setRxRawJitterUsec(MathStat value) {
    pjsua2JNI.RtcpStat_rxRawJitterUsec_set(swigCPtr, this, MathStat.getCPtr(value), value);
  }

  public MathStat getRxRawJitterUsec() {
    long cPtr = pjsua2JNI.RtcpStat_rxRawJitterUsec_get(swigCPtr, this);
    return (cPtr == 0) ? null : new MathStat(cPtr, false);
  }

  public void setPeerSdes(RtcpSdes value) {
    pjsua2JNI.RtcpStat_peerSdes_set(swigCPtr, this, RtcpSdes.getCPtr(value), value);
  }

  public RtcpSdes getPeerSdes() {
    long cPtr = pjsua2JNI.RtcpStat_peerSdes_get(swigCPtr, this);
    return (cPtr == 0) ? null : new RtcpSdes(cPtr, false);
  }

  public RtcpStat() {
    this(pjsua2JNI.new_RtcpStat(), true);
  }

}

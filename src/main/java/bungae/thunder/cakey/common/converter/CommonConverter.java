package bungae.thunder.cakey.common.converter;

/** 컨버터 인터페이스 */
public interface CommonConverter</* source = */ S, /* target*/ T> {

    T convert(S source);
}

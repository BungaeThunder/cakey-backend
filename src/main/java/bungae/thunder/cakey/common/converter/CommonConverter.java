package bungae.thunder.cakey.common.converter;

/** 컨버터 인터페이스 해당 인터페이스를 구현한 클래스의 이름은 다음 규칙을 따른다. {Target}Converter ex. CakeResponseDtoConverter */
public interface CommonConverter</* source = */ S, /* target*/ T> {
    T convert(S source);
}

package GoodWeather.model;

import lombok.Getter;
import lombok.Setter;

public class UserClothes {
    @Getter @Setter
    private Integer clothId;
    @Getter @Setter
    private String mailAddress;
    @Getter @Setter
    private String clothGenreId;
    @Getter @Setter
    private String clothName;
    @Getter @Setter
    private String clothColor;
    @Getter @Setter
    private String clothIcon;
}
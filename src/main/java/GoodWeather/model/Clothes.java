package GoodWeather.model;

import lombok.Getter;
import lombok.Setter;

public class Clothes {
    @Getter
    @Setter
    private String day_clothes_description;
    @Getter @Setter
    private String night_clothes_description;
    @Getter @Setter
    private ManClothes man_clothes;
    @Getter @Setter
    private WomanClothes woman_clothes;
}

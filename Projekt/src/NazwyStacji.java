public class NazwyStacji {
    public static int index;
    public static String[] nazwyStacji = {"Warszawa", "Kraków", "Łódź", "Wrocław", "Poznań", "Gdańsk", "Szczecin",
            "Bydgoszcz", "Lublin", "Katowice", "Białystok", "Gdynia", "Częstochowa", "Radom", "Sosnowiec", "Toruń",
            "Kielce", "Rzeszów", "Gliwice", "Zabrze", "Olsztyn", "Bielsko-Biała", "Bytom", "Zielona Góra", "Rybnik",
            "Tychy", "Gorzów Wielkopolski", "Dąbrowa Górnicza", "Elbląg", "Płock", "Opole", "Wałbrzych", "Krosno",
            "Przemyśl", "Zamość", "Krosno Odrzańskie", "Lubin", "Suwałki", "Kłodzko", "Świnoujście", "Siedlce",
            "Starachowice", "Ostrowiec Świętokrzyski", "Piła", "Słupsk", "Jaworzno", "Konin", "Stalowa Wola",
            "Nowy Sącz", "Bolesławiec", "Ełk", "Tarnobrzeg", "Mysłowice", "Grudziądz", "Świdnica", "Sieradz",
            "Kętrzyn", "Krotoszyn", "Nowa Sól", "Piekary Śląskie", "Tarnowskie Góry", "Włocławek", "Chorzów", "Mielec",
            "Kwidzyn", "Zduńska Wola", "Świecie", "Ostróda", "Inowrocław", "Chełm", "Giżycko", "Braniewo",
            "Strzelce Opolskie", "Bytów", "Sandomierz", "Tczew", "Wołomin", "Złotoryja", "Pleszew", "Oleśnica",
            "Wągrowiec", "Biała Podlaska", "Nysa", "Mława", "Puławy", "Grajewo", "Węgrów", "Piaseczno", "Świebodzin",
            "Nowogard", "Kępno", "Wodzisław Śląski", "Skarżysko-Kamienna", "Łomża", "Lubartów", "Gołdap", "Namysłów",
            "Skawina", "Strzegom", "Hajnówka", "Jarosław", "Zgorzelec", "Sulechów", "Kozienice", "Olecko", "Krasnystaw",
            "Krynica-Zdrój", "Kępice", "Przysucha", "Złocieniec", "Koszalin", "Iława", "Nowa Ruda", "Żagań", "Olecko"};

    public static String zwrocNazwe(){
        return nazwyStacji[index++];
    }
}

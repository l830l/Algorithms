import java.util.*;

class Song implements Comparable<Song>{
    int num;
    int count;

    Song(int num, int count){
        this.num = num;
        this.count = count;
    }

    @Override
    public int compareTo(Song o) {
        // count는 내림차순
        if (this.count != o.count) {
            return o.count - this.count;
        }

        // num은 오름차순
        return this.num - o.num;
    }

    @Override
    public String toString() {
        return "Song [num=" + num + ", count=" + count + "]";
    }
}

class Genre{
    String name;
    Song max;
    Song submax;
    int total;

    Genre(String name){
        this.name = name;
        this.total = 0;
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof Genre){
            return this.name.equals(((Genre)obj).name);
        }
        return false;
    }

    @Override
    public int hashCode() {
        return this.name.hashCode();
    }

    public void swap(){
        Song temp = this.max;
        this.max = this.submax;
        this.submax = temp;
    }

    @Override
    public String toString() {
        return "Genre [name=" + name + ", max=" + max + ", submax=" + submax + "]";
    }
}

class Solution {
    public int[] solution(String[] genres, int[] plays) {
        Map<String, Genre> genreMap = new HashMap<>();
        int len = genres.length;

        for(int i = 0; i < len; i++){
            Song song = new Song(i, plays[i]);
            Genre currentGenre = genreMap.get(genres[i]);

            if(currentGenre == null){
                currentGenre = new Genre(genres[i]);
                genreMap.put(genres[i], currentGenre);
                currentGenre.max = song;
            }

            else if(currentGenre.submax == null){
                currentGenre.submax = song;

                if(currentGenre.max.compareTo(currentGenre.submax) > 0) {
                    currentGenre.swap();
                }
            }
            else {
                if(currentGenre.max.compareTo(song) > 0){
                    currentGenre.swap();
                    currentGenre.max = song;
                }else if(currentGenre.submax.compareTo(song) > 0){
                    currentGenre.submax = song;
                }
            }
            currentGenre.total += song.count;
        }

        TreeSet<Genre> genreSet = new TreeSet<>(Comparator.comparing((Genre g) -> g.total).reversed());
        genreSet.addAll(genreMap.values());

        ArrayList<Integer> answer = new ArrayList<>();
        genreSet.stream().forEach(genre -> {
            answer.add(genre.max.num);
            if(genre.submax != null){
                answer.add(genre.submax.num);
            }
        });

        return answer.stream().mapToInt(i->i).toArray();
    }
}
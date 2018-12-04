package utils;


public class AnswerDuelMastermind {


    public final int nbFind;
    public final int nbWellPlaced;

    public AnswerDuelMastermind(int nbFind, int nbWellPlaced) {
        this.nbFind = nbFind;
        this.nbWellPlaced = nbWellPlaced;
    }


    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + nbFind;
        result = prime * result + nbWellPlaced;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        AnswerDuelMastermind other = (AnswerDuelMastermind) obj;
        if (this.nbFind != other.nbFind)
            return false;
        if (this.nbWellPlaced != other.nbWellPlaced)
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Answer [nbFind=" + nbFind + ", nbWellPlaced=" + nbWellPlaced + "]";
    }

}
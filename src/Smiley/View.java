import pt.iscte.guitoo.Color;
import pt.iscte.guitoo.StandardColor;
import pt.iscte.guitoo.board.Board;

public class View {
	PositionTrace model;
	Board board;

	View(PositionTrace model) {
		this.model = model;
		board = new Board("Tabuleiro", 4, 6, 40);
		board.setIconProvider(this::icon);
		board.addMouseListener(this::click);
		board.setBackgroundProvider(this::background);
		board.addAction("new", this::action);
	}

	// qual o ícone na posição (line, col)?
	// null significa não haver ícone
	String icon(int line, int col) {
		// TODO interrogar model para saber a posição atual
		return "happy.png";
	}

	// executa cada vez que é clicada uma posição do tabuleiro (line, col)
	void click(int line, int col) {
		// TODO fazer movimento
		board.showMessage(line + ", " + col);
		board.setTitle(line + ", " + col);
	}

	// qual a cor de fundo da posição (line, col)?
	Color background(int line, int col) {
		// TODO interrogar model para saber se a posição foi visitada
		return StandardColor.GRAY;
	}

	// executa quando o botão é primido
	void action() {
		int energy = board.promptInt("Energia?");
		if (energy >= 0) {
			PositionTrace newModel = new PositionTrace(energy);
			View gui = new View(newModel);
			gui.start();
		}
	}

	void start() {
		board.open();
	}

	public static void main(String[] args) {
		View gui = new View(new PositionTrace(10));
		gui.start();
	}
}
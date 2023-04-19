package model.aula;

public class SAClase {
	
	public boolean eliminarClase(EnumCurso curso, char letra) {

		DAOClase dao = new DAOClase();
		TransferClase transfer = dao.read(curso, letra);

		// si no existe no se elimina
		if (transfer != null) {

			dao.eliminate(curso, letra);

			return true;
		}

		return false;
	}

	public boolean crearClase(EnumCurso curso, TransferClase aTNew) {

		DAOClase dao = new DAOClase();
		TransferClase transfer = dao.read(curso, aTNew.getGrupo());
		// como no existe se añade a la bd
		if (transfer == null) {

			dao.create(curso, aTNew);

			return true;
		}
		return false;
	}

	public boolean editarClase(EnumCurso curso,TransferClase aTNew) {

		DAOClase dao = new DAOClase();
		TransferClase transfer = dao.read(curso, aTNew.getGrupo());

		// como no existe se edita la bd
		if (transfer == null) {

			dao.eliminate(curso, aTNew.getGrupo());
			dao.create(curso, aTNew);

			return true;
		}
		return false;
	}
}

export class Constants{
  public static readonly PATH_EMPTY = '';
  public static readonly PATH_LOGIN = 'login';
  public static readonly PATH_DOCTORS = 'doctors';
  public static readonly PATH_HOME = '';
  public static readonly PATH_APPOINTMENT = 'appointments';
  public readonly PATH_HOME = '';

  // TOAST MESSAGES
  public readonly TOAST_MSG_FAIL_SERVER_COMMUNICATION = 'Falha de comunicação com o servidor';
  public readonly TOAST_MSG_ERROR_LOGIN = 'Erro de login';
  public readonly TOAST_MSG_SUCCESS_SIGNUP = 'Usuário cadastrado com sucesso';
  public readonly TOAST_MSG_ERROR_SIGNUP = 'Erro ao cadastrar usuário';
  public readonly TOAST_MSG_ERROR_GET_LIST_DOCTORS = 'Erro ao carregar lista de médicos';
  public readonly TOAST_MSG_ERROR_SAVE_DOCTOR = 'Erro ao criar novo médico';
  public readonly TOAST_MSG_SUCCESS_SAVE_DOCTOR = 'Médico cadastrado com sucesso';
  public readonly TOAST_MSG_ERROR_GET_LIST_APPOINTMENTS = 'Erro ao carregar lista de consultas médicas';
  public readonly TOAST_MSG_SUCCESS_SAVE_APPOINTMENT = 'Consulta médica cadastrada com sucesso';
  public readonly TOAST_MSG_ERROR_SAVE_APPOINTMENT = 'Erro ao criar nova consulta médica';

  public readonly DICTIONARY = {
    'user.or.password.mismatch' : 'Usuário não cadastrado',
    'email.already.exists': 'Email já cadastrado',
    'crm.already.exists': 'CRM já cadastrado',
  };
}

import { Component, OnInit, ViewChild } from '@angular/core';
import { Appointments } from '../../shared/models/appointments.model';
import { MatTableDataSource } from '@angular/material/table';
import { MatPaginator } from '@angular/material/paginator';
import { MatSort } from '@angular/material/sort';
import { Constants } from 'src/app/shared/constants';
import { ToastrService } from 'ngx-toastr';
import { AppointmentsService } from './appointments.service';
import { DatePipe } from '@angular/common';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Doctor } from '../../shared/models/doctor.model';
import { logging } from 'protractor';
import { DoctorsService } from '../doctors/doctors.service';
import { threadId } from 'worker_threads';

@Component({
  selector: 'app-appointments',
  templateUrl: './appointments.component.html',
  styleUrls: ['./appointments.component.css'],
  providers: [DatePipe],
})
export class AppointmentsComponent implements OnInit {
  public dataSource = new MatTableDataSource<Appointments>();
  public displayedColumns: Array<string> = [
    'local',
    'date',
    'doctorName',
    'doctorCrm',
    'customerName',
    'customerRegister',
  ];

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  loading = false;

  formRegisterAppointment = new FormGroup({
    local: new FormControl('', [Validators.required, Validators.maxLength(255)]),
    date: new FormControl('', [Validators.required]),
    customerName: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    customerRegister: new FormControl('', [Validators.required, Validators.maxLength(10)]),
    doctorSelect: new FormControl(null, [Validators.required]),
  });

  doctors: Doctor[] = [];

  loadingForm = false;

  constructor(
    private toast: ToastrService,
    private constants: Constants,
    private appointmentsService: AppointmentsService,
    private doctorsService: DoctorsService,
    private datePipe: DatePipe,
  ) { }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.getAllAppointments();
    this.getAllDoctors();
  }

  public getAllAppointments(): void {
    this.loading = true;
    this.appointmentsService.findAll().subscribe(
      (resp) => {
        this.dataSource.data = resp;
        this.loading = false;
      },
      (err) => {
        this.loading = false;
        this.toast.error(this.constants.DICTIONARY[err.error.message],
          this.constants.TOAST_MSG_ERROR_GET_LIST_APPOINTMENTS);
      }
    );
  }

  public getAllDoctors(): void {
    this.loadingForm = true;
    this.doctorsService.findAll().subscribe(
      (resp) => {
        this.doctors = resp;
        this.loadingForm = false;
      },
      (err) => {
        this.loadingForm = false;
        this.toast.error(this.constants.DICTIONARY[err.error.message],
          this.constants.TOAST_MSG_ERROR_GET_LIST_DOCTORS);
      }
    );
  }

  public saveAppointment(): void {
    const obj = {
      id: 0,
      local: this.formRegisterAppointment.controls.local.value,
      date: this.datePipe.transform(this.formRegisterAppointment.controls.date.value, 'dd/MM/yyyy'),
      customer: {
        id: 0,
        name: this.formRegisterAppointment.controls.customerName.value,
        registerId: this.formRegisterAppointment.controls.customerRegister.value
      },
      doctor: this.formRegisterAppointment.controls.doctorSelect.value
    };
    this.loadingForm = true;
    this.appointmentsService.save(obj).subscribe(
      (success) => {
        this.loadingForm = false;
        this.formRegisterAppointment.reset();
        this.toast.success(this.constants.TOAST_MSG_SUCCESS_SAVE_APPOINTMENT);
        this.getAllAppointments();
      },
      (err) => {
        this.loadingForm = false;
        this.toast.error(this.constants.DICTIONARY[err.error.message],
          this.constants.TOAST_MSG_ERROR_SAVE_DOCTOR);
      }
    );
  }

  getErrorMessage(input): string {
    switch (input) {
      case 'local':
        return this.formRegisterAppointment.controls.local.hasError('required') ? 'Campo obrigatório' :
            this.formRegisterAppointment.controls.local.hasError('maxlength') ? 'Tamanho máximo de 255 caracteres' : '';
      case 'date':
        return this.formRegisterAppointment.controls.date.hasError('matDatepickerParse') ? 'Data inválida' :
            this.formRegisterAppointment.controls.date.hasError('required') ? 'Campo obrigatório' : '';
      case 'customerName':
        return this.formRegisterAppointment.controls.customerName.hasError('required') ? 'Campo obrigatório' :
            this.formRegisterAppointment.controls.customerName.hasError('maxlength') ? 'Tamanho máximo de 50 caracteres' : '';
      case 'customerRegister':
        return this.formRegisterAppointment.controls.customerRegister.hasError('required') ? 'Campo obrigatório' :
            this.formRegisterAppointment.controls.customerRegister.hasError('maxlength') ? 'Tamanho máximo de 10 caracteres' : '';
      case 'doctorSelect':
        return this.formRegisterAppointment.controls.doctorSelect.hasError('required') ? 'Campo obrigatório' : '';
    }
  }

}

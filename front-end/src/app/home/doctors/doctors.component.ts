import { Component, OnInit, ViewChild } from '@angular/core';
import { ToastrService } from 'ngx-toastr';
import { Constants } from 'src/app/shared/constants';
import { DoctorsService } from './doctors.service';
import { MatTableDataSource } from '@angular/material/table';
import { MatSort } from '@angular/material/sort';
import { MatPaginator } from '@angular/material/paginator';
import { LoadingViewAppService } from 'src/app/shared/services/loading-view-app-service';
import { Doctor } from 'src/app/shared/models';
import { FormGroup, FormControl, Validators } from '@angular/forms';

@Component({
  selector: 'app-doctors',
  templateUrl: './doctors.component.html',
  styleUrls: ['./doctors.component.css']
})
export class DoctorsComponent implements OnInit {
  public dataSource = new MatTableDataSource<Doctor>();
  public displayedColumns: Array<string> = [
    'name',
    'crm',
  ];

  @ViewChild(MatPaginator, { static: true }) paginator: MatPaginator;
  @ViewChild(MatSort, { static: true }) sort: MatSort;

  loading = false;

  formRegisterDoctor = new FormGroup({
    name: new FormControl('', [Validators.required, Validators.maxLength(50)]),
    crm: new FormControl('', [Validators.required, Validators.maxLength(8)]),
  });

  loadingRegisterDoctor = false;

  constructor(
    private toast: ToastrService,
    private constants: Constants,
    private doctorsService: DoctorsService,
    private loadingView: LoadingViewAppService,
  ) { }

  ngOnInit(): void {
    this.dataSource.paginator = this.paginator;
    this.dataSource.sort = this.sort;
    this.getAllDoctors();
  }

  public getAllDoctors(): void {
    this.loading = true;
    this.doctorsService.findAll().subscribe(
      (resp) => {
        this.dataSource.data = resp;
        this.loading = false;
      },
      (err) => {
        this.loading = false;
        this.toast.error(this.constants.DICTIONARY[err.error.message],
          this.constants.TOAST_MSG_ERROR_GET_LIST_DOCTORS);
      }
    );
  }

  public saveDoctor(){
    this.loadingRegisterDoctor = true;
    const obj = {
      id: 0,
      name: this.formRegisterDoctor.controls.name.value,
      crm: this.formRegisterDoctor.controls.crm.value
    };
    this.doctorsService.save(obj).subscribe(
      (success) => {
        this.loadingRegisterDoctor = false;
        this.formRegisterDoctor.reset();
        this.toast.success(this.constants.TOAST_MSG_SUCCESS_SAVE_DOCTOR);
        this.getAllDoctors();
      },
      (err) => {
        this.loadingRegisterDoctor = false;
        this.toast.error(this.constants.DICTIONARY[err.error.message],
          this.constants.TOAST_MSG_ERROR_SAVE_DOCTOR);
      }
    );
  }

  getErrorMessage(input): string {
    switch (input) {
      case 'name':
        return this.formRegisterDoctor.controls.name.hasError('required') ? 'Campo obrigatório' :
            this.formRegisterDoctor.controls.name.hasError('maxlength') ? 'Tamanho máximo de 50 caracteres' : '';
      case 'crm':
        return this.formRegisterDoctor.controls.crm.hasError('required') ? 'Campo obrigatório' :
            this.formRegisterDoctor.controls.crm.hasError('maxlength') ? 'Tamanho máximo de 50 caracteres' : '';
    }
  }

}

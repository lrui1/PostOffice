export interface User {
  id: number;
  username: string;
  password: string;
  salt: string;
  tel: string;
  address: string;
  postalCode: string;
  identity: number;
}

export interface Menu {
  id: number;
  title: string;
  iconUrl: string;
  
}

export interface Page<T> {
  currentPage: number;
  pageSize: number;
  totalPages: number; //一共多少页
  total: number; //一共多少条
  data: T[];
}

// 如果 spring 错误，返回的对象格式
export interface SpringError {
  timestamp: string,
  status: number,
  error: string,
  message: string,
  path: string
}

// 如果 spring 成功，返回 list 情况
export interface SpringObject<T> {
  code: number
  data: T,
  message?: string,
}

export interface SpringList<T> {
  code: number
  data: T[],
  message?: string,
}

// 如果 spring 成功，返回 page 情况
export interface SpringPage<T> {
  code: number
  data: { list: T[], total: number },
  message?: string,
}

// 如果 spring 成功，返回 string 情况
export interface SpringString {
  code: number
  data: string,
  message?: string,
}

import { AxiosResponse } from 'axios'
export interface AxiosRespError extends AxiosResponse<SpringError> { }
export interface AxiosRespList<T> extends AxiosResponse<SpringList<T>> { }
export interface AxiosRespPage<T> extends AxiosResponse<SpringPage<T>> { }
export interface AxiosRespString extends AxiosResponse<SpringString> { }
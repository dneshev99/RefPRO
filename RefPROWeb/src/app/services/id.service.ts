import { Injectable } from '@angular/core';

@Injectable()
export class IdService {
  private _refereeId: string;
  private _matchId: string;

  constructor() {
    this._refereeId = null;
    this._matchId = null;
  }


  get refereeId(): string {
    return this._refereeId;
  }

  set refereeId(value: string) {
    this._refereeId = value;
  }

  get matchId(): string {
    return this._matchId;
  }

  set matchId(value: string) {
    this._matchId = value;
  }
}

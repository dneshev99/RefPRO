import {Team} from './Team';
import {Player} from './Player';

export interface MatchInfo {
  id: string;
  isActive: boolean;
  competition: string;
  venue: string;
  date: string;
  time: string;
  home: Team;
  away: Team;
  homeAbbr: string;
  awayAbbr: string;
  homePlayers: Player[];
  awayPlayers: Player[];
  length: number;
  subsHome: Player[];
  subsAway: Player[];
}
